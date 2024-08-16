package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    public void addBook(@RequestBody NewBookRequest NewBookRequest){
        Book book = new Book();
        book.setName(NewBookRequest.name());
        book.setPublisher(NewBookRequest.publisher());
        book.setPublished_date(NewBookRequest.published_date());
        book.setStock(NewBookRequest.stock());
        book.setDescription(NewBookRequest.description());
        bookRepository.save(book);
    }
    public boolean BuyBook(Long id){
        Optional<Book> book = bookRepository.findById(id);
        if(isBookInStock(id) && book.isPresent()){
            Book bookn = book.get();
            bookn.setStock(bookn.getStock()-1);
            bookRepository.save(bookn);
            return true;
        }
        return false;
    }
    public boolean isBookInStock(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()){
            Book bookn = book.get();
            return bookn.isinStock();
        }
        return false;
    }
}
