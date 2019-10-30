package com.company.odataapp.service;

import com.company.odataapp.entity.Person;
import com.company.odataapp.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private PersonRepository repository;

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Person> findById(Long id) {

        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Person save(Person person) {
        return repository.save(person);
    }

    @Override
    public void modifyPerson(Person person) {
        Person modifyingPerson = repository.findById(person.getId()).get();
        person.setRole(modifyingPerson.getRole());
        repository.save(person);
    }
}
