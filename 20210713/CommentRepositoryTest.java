package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void CommentTest(){
//        Comment comment = commentRepository.findById(3L).get();

        Comment comment = new Comment();
        comment.setComment("별로에요");
//        comment.setCommentedAt(LocalDateTime.now());

        commentRepository.saveAndFlush(comment);

        entityManager.clear();

//        System.out.println(commentRepository.findById(3L).get());
        // findById 는 캐쉬를 사용해서 가져오는것이다.

//        commentRepository.findAll().forEach(System.out::println);

        System.out.println(comment);

        commentRepository.findAll().forEach(System.out::println);

        // 영속성 캐쉬로 인해 환불을 햇음에도 캐쉬가 남아있어ㅓㅅ 재환불되거나
        // 회원탈퇴를 했음에도 디비는 지워졌으나 디비 캐쉬가 남아서 로그인이 되는 현상이 간혹 발생하므로
        // find , save 전후 시점에 영속성 캐쉬가 어떻게 움직이는지 잘 학습해두어야한다.

    }

}