package ru.example.springmvc.controllers;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.example.springmvc.dao.MyPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import ru.example.springmvc.models.Person;

import javax.validation.Valid;
import java.sql.SQLException;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final MyPersonDAO personDAO;

    @Autowired
    public PeopleController(MyPersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getIndexPage(Model model) throws SQLException {
        model.addAttribute("people",personDAO.getPeople());
        return "/people/index";
    }

    @GetMapping("/{id}")
    public String getPersonByIdPage(@PathVariable("id") int id, Model model) throws SQLException {
        model.addAttribute("person",personDAO.getPerson(id));
        return "/people/person";
    }

    @GetMapping("/add")
    public String getAddPage(Model model){
        model.addAttribute("person",new Person());
        return "/people/add";
    }
    @PostMapping()
    public String addNewPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult result) throws SQLException {
        if(result.hasErrors()) return "/people/add";
        personDAO.addNewPerson(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) throws SQLException {
        model.addAttribute("person", personDAO.getPerson(id));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult result,
                         @PathVariable("id") int id) throws SQLException {
        if(result.hasErrors()) return "/people/edit";
        personDAO.update(person,id);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) throws SQLException {
        personDAO.deletePerson(id);
        return "redirect:/people";
    }
}
