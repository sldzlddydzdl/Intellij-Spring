package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value = "update book set category='none'", nativeQuery = true)
    void update(); // 이메서드를 실행하면 category 를 none 으로세팅해준다.

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse(); //DeletedFalse 를 항상 붙여야되고
                                                        // 실수로 DeletedFalse 가 누락되면 탈퇴한 회원의 정보가 보여지게되고 탈퇴한 회원이 접속할수있게 되는 이런 사이드 이펙트가 발생한다.
                                                        // 이런 실수를 방지하기위해 Book 위에 @Where(clause = "deleted = false") 를 써주면 모든 쿼리마다 where 조건을 걸어주게된다.
}
