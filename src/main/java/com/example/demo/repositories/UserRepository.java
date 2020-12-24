package com.example.demo.repositories;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
@Transactional
public interface UserRepository extends JpaRepository<UserEntity,Long> {
     @Query(value = "select       \n" +
             "             u.user_name,\n" +
             "             b.book_name\n" +
             "              from users u\n" +
             "             inner join user_book ub on u.id=ub.user_id\n" +
             "             inner join books b on b.id=ub.book_id",nativeQuery = true)
     List<Map<String,Object>> findAllUserBook();

}
