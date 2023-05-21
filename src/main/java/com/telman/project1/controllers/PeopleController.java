package com.telman.project1.controllers;

import com.telman.project1.dao.PersonDAO;
import com.telman.project1.models.Person;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getPeople(Model model) {
        model.addAttribute("people", personDAO.getPeople());
        return "people/index";
    }

    @GetMapping("/new")
    public String getAddPerson(@ModelAttribute("person") Person person ) {
        return "people/addPerson";
    }

    @GetMapping("/{id}")
    public String getPersonProfile(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonById(id));
        model.addAttribute("books", personDAO.getBooksByPersonId(id));
        return "people/profile";
    }

    @GetMapping("/{id}/edit")
    public String getPersonEdit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.getPersonById(id));
        return "people/editPerson";
    }

    @PostMapping()
    public String postAddPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "people/addPerson";
        personDAO.save(person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/edit")
    public String postEditPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                                 @PathVariable int id) {
        if (bindingResult.hasErrors()) return "people/editPerson";
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
