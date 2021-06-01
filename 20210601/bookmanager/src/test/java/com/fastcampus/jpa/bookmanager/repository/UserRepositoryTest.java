package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 컨택스트를 로딩을해서 테스트에 활용하겠다.
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void crud(){ // create , read ,update ,delete

        userRepository.save(new User()); // user 생성 하고 테이블에 저장하겠다.

        System.out.println(">>> " + userRepository.findAll());
//      >>> [User(id=1, name=null, email=null, createdAt=null, updateAt=null)]
    }

}