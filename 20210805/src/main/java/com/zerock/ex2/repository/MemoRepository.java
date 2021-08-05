package com.zerock.ex2.repository;

import com.zerock.ex2.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    Page<Memo> findByMnoBetween(Long from , Long to , Pageable pageable);

    void deleteMemoByMnoLessThan(Long num);

//    @Query(value = "select m.mno , m.memoText from Memo m where m.mno > :mno ")
////            ,
////            countQuery = "select count(m) from Memo m where m.mno > :mno")
//    List<Memo> getListWithQueryObject(Long mno);

    @Query("select m from Memo m where m.mno > :mno")
    List<Memo> getList(Long mno);
}
