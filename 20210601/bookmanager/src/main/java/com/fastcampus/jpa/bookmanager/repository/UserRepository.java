package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository<User, Long> 이 Spring-data jpa library 가 지원해주는 영역이다.

    // JpaRepository 에서 많은 Jpa 관련 메소드들을 지원해준다.

    // JpaRepository < Entity 타입 , Entity 의 primary key 값 > 이두개를
    // 제내릭 안에 넣어야한다.





}
