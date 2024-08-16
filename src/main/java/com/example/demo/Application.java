package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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


}
