package com.fastcampus.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void test(){
        User user = new User();
        user.setEmail("martine@fastcampus.com");
        user.setName("martine");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());


        User user1 = new User(null ,"martine" , "martine@fastcampus.com", LocalDateTime.now(), LocalDateTime.now());
        User user2 = new User("martine" , "martine@fastcampus.com");

        User user3 =User.builder()
                .name("martine")
                .email("martine@fastcampus.com")
                .build();

        System.out.println(">>> " + user) ;
    }
}