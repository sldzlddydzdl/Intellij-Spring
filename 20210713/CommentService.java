package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Comment;
import com.fastcampus.jpa.bookmanager.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init(){
        for(int i = 0 ; i < 10; i++){
            Comment comment = new Comment();
            comment.setComment("최고에요");

            commentRepository.save(comment);
        }
    }

//    @Transactional
    public void updateSomething(){
        List<Comment> comments = commentRepository.findAll();

        for(Comment comment : comments){
            comment.setComment("별로에요");

            // @Transactional 로 인해 영속성 컨택스트가 이함수를 관리하게되므로 save 함수를 하지 않아도 영속성 안에 포함되므로
            // 저절로 update 쿼리문을 만들어 준다.
//            commentRepository.save(comment);
        }
    }

    @Transactional(readOnly = true)
    public void insertSomething(){
//        Comment comment = new Comment();
        Comment comment= commentRepository.findById(1L).get();
        comment.setComment("이건뭐죠?");

//        commentRepository.save(comment); // 객체는 영속성 dirty check 를 안해주므로 그래서 따로 save 를 해줘야 insert 문이 실행된다.
    }
}
