package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "user_book")
public class UserBookEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


//    @ManyToOne
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private UserEntity user;
//
//
//    @ManyToOne
//    @JoinColumn(name = "book_id", insertable = false, updatable = false)
//    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

}
