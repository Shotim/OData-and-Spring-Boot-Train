package com.company.odataapp.service;

import com.company.odataapp.entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {

    List<Person> findAll();

    Optional<Person> findById(Long id);

    void deleteById(Long id);

    Person save(Person person);

    void modifyPerson(Person person);
}
