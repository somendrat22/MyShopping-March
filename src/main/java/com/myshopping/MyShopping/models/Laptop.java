package com.myshopping.MyShopping.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Laptop {
    @Id
    UUID id;
    String name;
    @ManyToMany
    List<Student> student;
}
