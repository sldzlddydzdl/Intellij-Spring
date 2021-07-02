package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test(){
        userService.put();

        System.out.println(">>>" + userRepository.findByEmail("newUser@fastcampus.com"));

//        Hibernate:
//        select
//        user0_.id as id1_7_,
//                user0_.created_at as created_2_7_,
//        user0_.updated_at as updated_3_7_,
//                user0_.email as email4_7_,
//        user0_.gender as gender5_7_,
//                user0_.name as name6_7_
//        from
//        user user0_
//        where
//        user0_.email=?
//        >>>null

        userRepository.findAll().forEach(System.out::println);

    }

}