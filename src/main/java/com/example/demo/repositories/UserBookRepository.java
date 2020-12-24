package com.example.demo.repositories;

import com.example.demo.entity.UserBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserBookRepository extends JpaRepository<UserBookEntity,Long> {
   UserBookEntity findByUserIdAndBookId(Long user_id, Long book_id);
}
