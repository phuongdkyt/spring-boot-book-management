package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String book_name;
    private String author;
    private Double price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private Set<UserBookEntity> bookEntities;
}
