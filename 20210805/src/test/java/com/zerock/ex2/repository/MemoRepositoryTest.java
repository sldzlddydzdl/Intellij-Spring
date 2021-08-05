package com.zerock.ex2.repository;

import com.zerock.ex2.entity.Memo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @DisplayName("1. 기본테스트")
    @Test
    void test_1(){

        System.out.println(memoRepository.getClass().getName());

    }

    @DisplayName("2. 데이터주입 테스트")
    @Test
    void test_2(){
        IntStream.rangeClosed(1, 100).forEach(i ->{
            Memo memo =  Memo.builder()
                    .memoText("Sample..." + i)
                    .build();

            memoRepository.save(memo);
        });
    }

    @DisplayName("3. 데이터 조회 테스트")
    @Test
    void test_3(){
        Long mno = 100L;
        Optional<Memo> result = memoRepository.findById(mno);

        if(result.isPresent()){
            System.out.println(result.get());
            System.out.println(result.get().getMno() + " " + result.get().getMemoText());
        }
        else{
            System.out.println("데이터가 존재하지 않습니다.");
        }
    }

    @Transactional
    @DisplayName("3.1 데이터 조회 테스트")
    @Test
    void test_3_1(){

        Long mno = 102L;

        Memo result = memoRepository.getOne(mno);

        System.out.println("=================");

        System.out.println(result);
    }

    @DisplayName("4 update 테스트")
    @Test
    void test_4(){
        Long mno = 100L;

        Memo update = Memo.builder().
                mno(mno).
                memoText("update Text")
                .build();

        memoRepository.save(update);

        System.out.println(update);
    }


//    insert 하기전에도 select 해서 없으면 insert 잇으면 update
    @DisplayName("4.1 insert select 테스트")
    @Test
    void test_4_1(){
        Memo memo = Memo.builder().
                mno(101L).
                memoText("select 를햇을까").
                build();

        memoRepository.save(memo);

        System.out.println("======================================");

        System.out.println(memo);

    }

    @DisplayName("5. 데이터 삭제 테스트")
    @Test
    void test_5(){

        memoRepository.deleteById(101L);

    }

    @DisplayName("6. Page 테스트")
    @Test
    void test_6(){
        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result =  memoRepository.findAll(pageable);

        System.out.println(result);

        System.out.println("Total Pages : " + result.getTotalPages());

        System.out.println("Total Count : " + result.getTotalElements());

        System.out.println("Page Number : " + result.getNumber());

        System.out.println("Page Size : " + result.getSize());

        System.out.println("has next Page? : " + result.hasNext());

        System.out.println("first Page? : " + result.isFirst());

        System.out.println("------------------------------");

        for(Memo memo : result.getContent())
            System.out.println(memo);
    }

    @DisplayName("7. page sort test")
    @Test
    void test_7(){
        Sort sort1 = Sort.by("mno").descending();

        Pageable pageable = PageRequest.of(0 ,10 , sort1);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(memo->{
            System.out.println(memo);
        });
    }

    @DisplayName("8. page and sort test")
    @Test
    void test_8(){

        Memo memo = Memo.builder()
                .mno(100L)
                .memoText("1")
                .build();

        memoRepository.save(memo);


        Sort sort1 = Sort.by("mno").descending();
        Sort sort2 = Sort.by("memoText").ascending();
        Sort sortAll = sort1.and(sort2);

        Pageable pageable = PageRequest.of(0,10,sortAll);

        Page<Memo> result = memoRepository.findAll(pageable);

        result.get().forEach(mem ->{
            System.out.println(mem);
        });
    }

    @DisplayName("9 쿼리 메소드 테스트")
    @Test
    void test_9(){
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L , 80L);

        for(Memo memo : list){
            System.out.println(memo);
        }
    }

    @DisplayName("10 페이징 메소드 테스트")
    @Test
    void test_10() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").desvcending());
        Pageable pageable = PageRequest.of(0, 10);

        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);

        result.get().forEach(memo ->{
            System.out.println(memo);
        });

        List<Memo> result2 = memoRepository.getList(10L);

        result2.forEach(System.out::println);

//        result2.get().forEach(memo->{
//            Arrays.stream(memo).filter(s->{
//                if(s.getClass().equals("Memo"))
//
//            });
//        });
    }

    @Commit
    @Transactional
    @DisplayName("11. 쿼리메소드 delete 테스트")
    @Test
    void test_11(){
        memoRepository.deleteMemoByMnoLessThan(10L);
    }
}
