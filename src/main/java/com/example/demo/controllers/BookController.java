package com.example.demo.controllers;


import com.example.demo.entity.BookEntity;
import com.example.demo.entity.bo.BaseMessage;
import com.example.demo.entity.bo.ResponseEntityBO;
import com.example.demo.services.IBookService;
import com.example.demo.utils.Common;
import com.example.demo.utils.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    Logger logger = Logger.getLogger("BookController");

    @Autowired
    IBookService bookService;

    //http://localhost:8082/book/search?name=xxx
    @GetMapping("/search")
    public Object read(@RequestParam String name) {
        BaseMessage response;
        long timeStamp = Common.getTimeStamp();
        try {
            List<BookEntity> results = bookService.findAllByBookName(name);
            if (Common.isNullOrEmpty(results)) {
                response = new ResponseEntityBO<>(Constants.ERROR_RESPONSE, "Thất bại!!", timeStamp, results);
                logger.error(Common.createMessageLog(name, response, null, timeStamp, "read"));
            } else {
                response = new ResponseEntityBO<>(Constants.SUCCESS_RESPONSE, "Thành công!", timeStamp, results);
                logger.info(Common.createMessageLog(name, response, null, timeStamp, "read"));
            }

        } catch (Exception e) {
            response = new BaseMessage(Constants.ERROR_RESPONSE, e.getMessage(), timeStamp);
            logger.error(Common.createMessageLog(name, response, null, timeStamp, "read"));
        }
        return response;
    }
}
