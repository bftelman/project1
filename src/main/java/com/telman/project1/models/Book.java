package com.telman.project1.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int book_id;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Book name should be between 2 and 100 symbols")
    private String name;

    @NotEmpty
    @Size(min = 2, max = 100, message = "Author name should be between 2 and 100 symbols")
    private String author;

    @Min(value = 1500, message = "Year shouldn't be less than 1500")
    private int year;

    public Book() {}

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
