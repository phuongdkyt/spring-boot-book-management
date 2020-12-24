package com.example.demo.services.impl;


import com.example.demo.entity.BookEntity;
import com.example.demo.entity.UserBookEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.excel.ExcelConvert;
import com.example.demo.exceptions.FileStorageException;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.UserBookRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.IUserService;

import com.example.demo.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserBookRepository userBookRepository;

    @Override
    public List<UserEntity> findAll() {
      return  userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        // Load file from database
        return userRepository.findById(id);

    }

    @Override
    public boolean updateUser(UserEntity user) {
        Optional<UserEntity> userEntity = userRepository.findById(user.getId());
        if (userEntity.isPresent()) {
            userEntity.get().setUser_name(user.getUser_name());
            userEntity.get().setAddress(user.getAddress());
            userEntity.get().setAge(user.getAge());
            userRepository.save(userEntity.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addListUser(MultipartFile file) {
        try {
            List<UserEntity> userEntityList = ExcelConvert.excelToUsers(file.getInputStream());
            userRepository.saveAll(userEntityList);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteUserById(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (userEntity.isPresent()) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addUser(UserEntity user) {
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean updateProfileImg(MultipartFile file, Long id) throws IOException {

        File convertFile =
                new File("src/main/resources/static/avatars/" + id + ".png");
        convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile,false);

        fos.write(file.getBytes());
        fos.close();
        Optional<UserEntity> userEntity=userRepository.findById(id);
        userEntity.get().setAvatar("/avatars/" + id + ".png");
        userRepository.save(userEntity.get());
        return true;
    }

    @Override
    public boolean readBook(Long user_id, Long book_id) {
        try {
            UserBookEntity userBookEntity= userBookRepository.findByUserIdAndBookId(user_id,book_id);
            if(Common.isNullOrEmpty(userBookEntity)) {
                Optional<UserEntity> userEntity= userRepository.findById(user_id);
                Optional<BookEntity> bookEntity=bookRepository.findById(book_id);
                UserBookEntity newUserBook= new UserBookEntity();
                newUserBook.setBook(bookEntity.get());
                newUserBook.setUser(userEntity.get());
                userBookRepository.save(newUserBook);

            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> getListUserAreReading() {
        return userRepository.findAllUserBook();
    }
}
