package com.company.odataapp.controller;

import com.company.odataapp.entity.Person;
import com.company.odataapp.entity.Role;
import com.company.odataapp.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class PersonController {

    private PersonService personService;

    @GetMapping("/users")
    public List<Person> showPeople() {
        return personService.findAll();
    }

    @PostMapping("/users")
    public void addPerson(@RequestBody Person person) {
        person.setRole(Role.ADMIN);
        personService.save(person);
    }

    @GetMapping("/users/{id}")
    public Person showPerson(@PathVariable("id") long id) {
        return personService.findById(id).get();
    }

    @PutMapping("/users/{id}")
    public void modifyPerson(@PathVariable("id") long id, @RequestBody Person person) {
        person.setId(id);
        personService.modifyPerson(person);
    }

    @DeleteMapping("/users/{id}")
    public void deletePerson(@PathVariable("id") long id) {
        personService.deleteById(id);
    }
}
