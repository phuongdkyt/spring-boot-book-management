package com.example.demo.repositories;

import com.example.demo.entity.UserEntity;
import com.example.demo.payload.UserBookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
     @Query(value = "select \n" +
             "u.user_name,\n" +
             "b.book_name\n" +
             " from users u\n" +
             "inner join users_books ub on u.id=ub.user_id\n" +
             "inner join books b on b.id=ub.book_id",nativeQuery = true)
     List<UserBookResponse> findAllUserBook();
     Optional<UserEntity> findById(Long id);
}
