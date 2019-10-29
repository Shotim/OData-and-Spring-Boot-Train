package com.company.odataapp.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "people")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @Column(name = "firstName")
    String firstName;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "email")
    String email;

    @Column(name = "role")
    Role role;



}
