package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void transactionTest(){
        try {
            bookService.putBookAndAuthor();
//            bookService.put();
        }
        catch(RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }
//        catch (Exception e){
//            System.out.println(">>> " + e.getMessage());
//        }

        // RuntimeException 의 경우에는 위의처럼 catch 문이 존재하지 않더라도 처리가 가능한 상태가되는데
        // (CheckedException) Exception 의 경우에는 반드시 Exception 에 대해서 처리를 하고 핸들링을 하도록 가이드가 되있다.
        // rollback 이란 행위조차도 개발자가 catch 문에서 exception 을 catch 를 하고 핸들링 하는 과정에서 rollback 을 하도록 가이드 되어있다.
        // 이부분을 의외로 잘모르거나 현업에서도 실제로 실수가 빈번한 캐이스중에 하나이기 때문에 꼭 숙지하고있어야한다.

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("authors : " + authorRepository.findAll());
    }

    @Test
    void isolationTest() {
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>> " + bookRepository.findAll()); // get 에서 Transaction 이 끝이나고
                                                            // 별도로 나와서 실행했기 때문에 여기에서는
            // Mysql 에서 category 를 none 으로 commit 한게 Transaction 이 끝나서 반영이되서 출력하면
        // category = none 이 반영되것이 그대로 출력된다.

    }
}

    // 이론적으로는 rollback 처리가 되는데 CheckedException 은 왜 rollback 처리가 되지 않을까 그냥 지식적으로 알아도되고 외워도된다.
