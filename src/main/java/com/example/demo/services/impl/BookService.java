package com.example.demo.services.impl;

import com.example.demo.entity.BookEntity;
import com.example.demo.repositories.BookRepository;
import com.example.demo.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<BookEntity> findAllByBookName(String name) {
        return bookRepository.findAllByName(name);
    }
}
