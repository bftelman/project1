package com.telman.project1.dao;

import com.telman.project1.models.Book;
import com.telman.project1.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Person WHERE person_id=?",
                new BeanPropertyRowMapper<>(Person.class), id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullname, birth_year) VALUES(?, ?)",
                person.getFullname(), person.getBirth_year());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET fullname=?, birth_year=? WHERE person_id=?", person.getFullname(),
                person.getBirth_year(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE person_id = ?", id);
    }

    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * from Book where person_id = ?",
                new BeanPropertyRowMapper<Book>(Book.class), id);
    }
}
