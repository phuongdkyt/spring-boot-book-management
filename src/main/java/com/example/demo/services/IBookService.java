package com.example.demo.services;

import com.example.demo.entity.BookEntity;

import java.util.List;

public interface IBookService {

     List<BookEntity> findAllByBookName(String name);
}
