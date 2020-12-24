package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "books")
@NoArgsConstructor
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String book_name;
    private String author;
    private Double price;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    private Set<UserBookEntity> bookEntities;


    public BookEntity(String book_name, String author, Double price) {
        this.book_name = book_name;
        this.author = author;
        this.price = price;
    }
}
