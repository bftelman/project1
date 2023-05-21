package com.telman.project1.models;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int person_id;

    @NotEmpty(message = "Person name can't be empty")
    @Size(min = 2, max = 100, message = "Person name should be between 2 and 100 symbols")
    private String fullname;

    @Min(value = 1900, message = "Person birthyear should be greater than 1900")
    private int birth_year;

    public Person(String fullname, int birth_year) {
        this.fullname = fullname;
        this.birth_year = birth_year;
    }

    public Person() {
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }
}
