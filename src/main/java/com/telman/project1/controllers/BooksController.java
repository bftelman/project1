package com.telman.project1.controllers;

import com.telman.project1.dao.BookDAO;
import com.telman.project1.dao.PersonDAO;
import com.telman.project1.models.Book;
import com.telman.project1.models.Person;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("books", bookDAO.getBooks());
        model.addAttribute("url", "books");
        return "books/index";
    }

    @GetMapping("/new")
    public String getAddBook(@ModelAttribute("book") Book book) {
        return "books/addBook";
    }

    @GetMapping("/{id}")
    public String getBookPage(@PathVariable("id") int id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getBookById(id));
        Optional<Person> owner = bookDAO.getBookReader(id);

        if (owner.isPresent())
            model.addAttribute("owner", owner.get());
        else
            model.addAttribute("people", personDAO.getPeople());
        return "books/bookPage";
    }

    @GetMapping("/{id}/edit")
    public String getBookEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getBookById(id));
        return "books/editBook";
    }

    @PostMapping()
    public String postAddBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "books/addBook";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/edit")
    public String postEditBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                               @PathVariable int id) {
        if (bindingResult.hasErrors()) return "books/editBook";
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookDAO.releaseBook(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person selectedPerson) {
        bookDAO.assignBookToReader(selectedPerson, id);
        return "redirect:/books/" + id;
    }
}
