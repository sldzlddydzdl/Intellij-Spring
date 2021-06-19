package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("Jpa 초격차 패키지");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());

//            create table book (
//                    id bigint not null,
//                    created_at timestamp,
//                    updated_at timestamp,
//                    author_id bigint,
//                    category varchar(255),
//                    name varchar(255),
//                    publisher_id bigint,
//                    primary key (id)
//        )
    }
}
