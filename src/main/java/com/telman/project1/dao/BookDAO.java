package com.telman.project1.dao;

import com.telman.project1.models.Book;
import com.telman.project1.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Book WHERE book_id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, year, author) VALUES(?, ?, ?)", book.getName(), book.getYear(), book.getAuthor());
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name=?, year=?, author=? WHERE book_id=?", book.getName(), book.getYear(), book.getAuthor(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?", id);
    }

    public void assignBookToReader(Person person, int book_id) {
        jdbcTemplate.update("UPDATE Book SET person_id=? WHERE book_id=?", person.getPerson_id(), book_id);
    }

    public void releaseBook(int book_id) {
        jdbcTemplate.update("UPDATE Book SET person_id=NULL WHERE book_id=?", book_id);
    }

    public Optional<Person> getBookReader(int id) {
        return jdbcTemplate.query("SELECT Person.* from Book " +
                "JOIN Person on Book.person_id = Person.person_id WHERE Book.book_id=?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny();
    }
}
