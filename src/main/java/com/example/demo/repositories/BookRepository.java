package com.example.demo.repositories;

import com.example.demo.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
    @Query(value = "select * from books where book_name like \"%\" :name  \"%\"    ",nativeQuery = true)
    List<BookEntity> findAllByName(String name);
}
