package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RestController
public class Application {
    private final BookRepository BookRepository;
    @Autowired
    private BookService bookService;

    public Application(com.example.demo.BookRepository bookRepository) {
        BookRepository = bookRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @PostMapping("/post")
    public void addNew(@RequestBody NewBookRequest NewBookRequest){
        bookService.addBook(NewBookRequest);
    }
    @GetMapping("in-stock")
    public boolean isBookInStock(@RequestParam Long id) {
        return bookService.isBookInStock(id);
    }
    @GetMapping("/buy")
    public boolean Buy(@RequestParam Long id){
        return bookService.BuyBook(id);
    }
    @GetMapping("/find-name")
    public Book findName(@RequestParam String name){
        return BookRepository.findBookByName(name);
    }
    @GetMapping("/Display")
    public List<Book> Display(){return BookRepository.findAll();}
    @GetMapping("/find-by-publisher")
    public ResponseEntity<List<Book>> findPublisher(@RequestParam String pubName){ return bookService.getPubBooks(pubName); }
    @GetMapping("/find-by-date")
    public ResponseEntity<List<Book>> findDate(@RequestParam("startDate") String startDateStr, @RequestParam("endDate") String endDateStr){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date bDate1 = formatter.parse(startDateStr);
            Date bDate2 = formatter.parse(endDateStr);
            return bookService.getBookDate(bDate1, bDate2);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/Change-name")
    public ResponseEntity<Book> changeEntities(@RequestBody Book book){ return bookService.changeEntities(book); }

}
