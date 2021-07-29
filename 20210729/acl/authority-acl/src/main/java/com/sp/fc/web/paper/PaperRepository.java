package com.sp.fc.web.paper;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaperRepository extends JpaRepository<Paper , Long> {

//    @Cacheable("papers") // 기본적으로 스프링에 캐쉬는 aop 를 쓴다 그래서 메소드가 서버든 어디든 서비스든 아니던 다른 컨트롤러이든
    // 이 repository findById 로 paper 를 가져오게되면 id 가 키 가되는 해쉬맵이 만들어지고 그 캐쉬가 만들어지고 캐쉬의이름은 papers 가 된다.
    Optional<Paper> findById(Long id);




}
