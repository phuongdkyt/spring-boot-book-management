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
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String user_name;
    private String address;
    private Integer age;
    @JsonIgnore
    private String fileName;
    @Lob
    private byte[] avatar;


    @OneToMany(mappedBy = "user")
    Set<UserBookEntity> userBookEntities=new HashSet<>();
}
