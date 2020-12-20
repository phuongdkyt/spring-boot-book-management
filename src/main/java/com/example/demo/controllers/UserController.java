package com.example.demo.controllers;

import com.example.demo.entity.UserEntity;
import com.example.demo.entity.bo.BaseMessage;
import com.example.demo.entity.bo.ResponseEntityBO;
import com.example.demo.payload.UserBookResponse;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.impl.UserService;
import com.example.demo.utils.Common;
import com.example.demo.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = Logger.getLogger("UserController");
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public BaseMessage read(@PathVariable Long id) {
        BaseMessage response;
        long timeStamp = Common.getTimeStamp();
        try {
            Optional<UserEntity> results = userService.findById(id);
            if (Common.isNullOrEmpty(results)) {
                response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results.get());
                logger.info(Common.createMessageLog(null, response, null, timeStamp, "read"));
            } else {
                response = new ResponseEntityBO<>(Constants.ERROR_RESPONSE, "that bai!", timeStamp, results.get());
                logger.error(Common.createMessageLog(null, response, null, timeStamp, "read"));
            }

        } catch (Exception e) {
            response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
            logger.error(Common.createMessageLog(null, response, null, timeStamp, "read"));
        }
        return response;
    }

    @PostMapping("/add")
    public BaseMessage create(@RequestBody UserEntity userEntity) {
        BaseMessage response;
        long timeStamp = Common.getTimeStamp();
        try {
            boolean results = userService.addUser(userEntity);
            if (results == true) {
                response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results);
                logger.info(Common.createMessageLog(userEntity, response, null, timeStamp, "create"));
            } else {
                response = new ResponseEntityBO<>(Constants.ERROR_RESPONSE, "that bai!", timeStamp, results);
                logger.error(Common.createMessageLog(userEntity, response, null, timeStamp, "create"));
            }

        } catch (Exception e) {
            response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
            logger.error(Common.createMessageLog(userEntity, response, null, timeStamp, "create"));
        }
        return response;
    }

    //update user
    @PutMapping("/update")
    public BaseMessage update(@RequestBody UserEntity userEntity) {
        BaseMessage response;
        long timeStamp = Common.getTimeStamp();
        try {
            boolean results = userService.updateUser(userEntity);
            if (results == true) {
                response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results);
                logger.info(Common.createMessageLog(userEntity, response, null, timeStamp, "update"));
            } else {
                response = new ResponseEntityBO<>(Constants.ERROR_RESPONSE, "khong tim thay nguoi dung nay!", timeStamp, results);
                logger.error(Common.createMessageLog(userEntity, response, null, timeStamp, "update"));
            }

        } catch (Exception e) {
            response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
            logger.error(Common.createMessageLog(userEntity, response, null, timeStamp, "update"));
        }
        return response;
    }

    //update profile
    @PutMapping("/{user_id}/update/avatar")
    public BaseMessage update(@RequestParam("avatar") MultipartFile avatar, @PathVariable Long user_id) {
        BaseMessage response;
        long timeStamp = Common.getTimeStamp();
        try {
            boolean results = userService.updateProfileImg(avatar, user_id);
            if (results == true) {
                response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results);
                logger.info(Common.createMessageLog(null, response, null, timeStamp, "update"));
            } else {
                response = new ResponseEntityBO<>(Constants.ERROR_RESPONSE, "khong tim thay nguoi dung nay!", timeStamp, results);
                logger.error(Common.createMessageLog(null, response, null, timeStamp, "update"));
            }

        } catch (Exception e) {
            response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
            logger.error(Common.createMessageLog(null, response, null, timeStamp, "update"));
        }
        return response;
    }

    //upload list user by file excel
    @PostMapping("/upload")
    public BaseMessage create(@RequestParam("excel") MultipartFile file) {
        BaseMessage response;
        long timeStamp = Common.getTimeStamp();
        try {
            boolean results = userService.addListUser(file);
            if (results) {
                response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công", timeStamp, results);
                logger.info(Common.createMessageLog(null, response, null, timeStamp, "update"));
            } else {
                response = new ResponseEntityBO<>(Constants.ERROR_RESPONSE, "khong tim thay nguoi dung nay!", timeStamp, results);
                logger.error(Common.createMessageLog(null, response, null, timeStamp, "update"));
            }

        } catch (Exception e) {
            response = new BaseMessage(Constants.ERROR_RESPONSE, "Không xác định", timeStamp);
            logger.error(Common.createMessageLog(null, response, null, timeStamp, "update"));
        }
        return response;
    }


    @GetMapping("/all")
    List<UserBookResponse> findAllUserBook() {
        return userRepository.findAllUserBook();
    }

//    @GetMapping("/book/{book_id}/user/{user_id}")
//    boolean update(@PathVariable Long user_id, @PathVariable Long book_id) {
//        userService.readBook(user_id, book_id);
//        return true;
//    }
}
