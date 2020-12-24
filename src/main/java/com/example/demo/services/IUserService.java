package com.example.demo.services;

import com.example.demo.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IUserService {
    Optional<UserEntity> findById(Long id);
    boolean updateUser(UserEntity user);
    boolean addListUser(MultipartFile file);
    boolean deleteUserById(Long id);
    boolean addUser(UserEntity user);
    boolean updateProfileImg(MultipartFile file,Long id) throws IOException;
    boolean readBook(Long user_id,Long book_id);
    List<Map<String,Object>> getListUserAreReading();
}
