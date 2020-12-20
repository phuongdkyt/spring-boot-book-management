package com.example.demo.services.impl;


import com.example.demo.entity.BookEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.excel.ExcelConvert;
import com.example.demo.exceptions.FileStorageException;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

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
    public boolean updateProfileImg(MultipartFile file, Long id) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Kiểm tra xem tên của tệp có chứa các ký tự không hợp lệ không
            if (fileName.contains("..")) {
                throw new FileStorageException("Tên tệp chứa chuỗi đường dẫn không hợp lệ " + fileName);
            }
            Optional<UserEntity> userEntity = userRepository.findById(id);
            if (userEntity.isPresent()) {
                userEntity.get().setAvatar(file.getBytes());
                userEntity.get().setFileName(fileName);

            } else {
                return false;
            }
            userRepository.save(userEntity.get());
            return true;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public boolean readBook(Long userId, Long bookId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        Optional<BookEntity> bookEntity = bookRepository.findById(bookId);
//        userEntity.get().setBookEntitySet(Collections.singleton( bookEntity.get()));
        userRepository.save(userEntity.get());
        return true;
    }
}
