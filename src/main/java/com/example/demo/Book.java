package com.example.demo;


import javax.persistence.*;

import java.util.Date;
@Entity(name = "Book")
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "publisher",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String Publisher;
    @Column(
            name = "published_date",
            nullable = false
    )
    private Date published_date;
    @Column(
            name = "stock",
            nullable = false
    )
    private Integer Stock;
    @Column(
            name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String Description;

    public Book (){}

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer stock) {
        Stock = stock;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        name = Name;
    }
    public Book(String Name, String publisher, Date publishedDate, String description) {
        name = Name;
        Publisher = publisher;
        published_date = publishedDate;
        Description = description;
    }

    public boolean isinStock(){
        return getStock() > 0;
    }


}
