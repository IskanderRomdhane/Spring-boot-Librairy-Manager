package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void addBook(@RequestBody NewBookRequest NewBookRequest) {
        Book book = new Book();
        book.setName(NewBookRequest.name());
        book.setPublisher(NewBookRequest.publisher());
        book.setPublished_date(NewBookRequest.published_date());
        book.setStock(NewBookRequest.stock());
        book.setDescription(NewBookRequest.description());
        bookRepository.save(book);
    }

    public boolean BuyBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (isBookInStock(id) && book.isPresent()) {
            Book bookn = book.get();
            bookn.setStock(bookn.getStock() - 1);
            bookRepository.save(bookn);
            return true;
        }
        return false;
    }

    public boolean isBookInStock(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Book bookn = book.get();
            return bookn.isinStock();
        }
        return false;
    }

    public ResponseEntity<Book> changeEntities(Book book) {
        Optional<Book> oBook = bookRepository.findById(book.getId());
        if (oBook.isPresent()) {
            Book newBook = oBook.get();
            newBook.setName(book.getName());
            bookRepository.save(newBook);
            return ResponseEntity.ok(newBook);
        } else {
            return  ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<List<Book>> getPubBooks(String Publisher){
        List<Book> Books = bookRepository.findAll();
        List<Book> rBooks = new ArrayList<>();
        for (Book book: Books) {
            if (book.getPublisher().equals(Publisher)){rBooks.add(book);}
        }
        if (rBooks.isEmpty()){return ResponseEntity.notFound().build();}
        else{return ResponseEntity.ok(rBooks);}
    }

    public ResponseEntity<List<Book>> getBookDate(Date bDate1 , Date bDate2){
        List<Book> Books = bookRepository.findAll();
        List<Book> rBooks = new ArrayList<>();
        Date date = new Date();
        for (Book book: Books) {
            date = book.getPublished_date();
            if ( date.after(bDate1) && date.before(bDate2) ){rBooks.add(book);}
        }
        if (rBooks.isEmpty()){return ResponseEntity.notFound().build();}
        else{return ResponseEntity.ok(rBooks);}
    }
}