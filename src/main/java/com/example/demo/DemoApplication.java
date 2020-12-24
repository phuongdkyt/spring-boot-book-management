package com.example.demo;

import com.example.demo.entity.BookEntity;
import com.example.demo.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (bookRepository.count() <= 0) {
            List<BookEntity> listBooks = new ArrayList<>();
            listBooks.add(new BookEntity("Vợ nhặt", "Kim Lân", 22000.0));
            listBooks.add(new BookEntity("Lão Hạc", "Nam Cao", 55000.0));
            listBooks.add(new BookEntity("Truyện Kiều", "Nguyễn Du", 88000.0));
            listBooks.add(new BookEntity("Truyện Người Con Gái Nãm Xương", "Nguyễn Dữ", 66000.0));
            bookRepository.saveAll(listBooks);
        }

    }
}
