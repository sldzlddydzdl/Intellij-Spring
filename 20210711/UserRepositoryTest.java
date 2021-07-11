package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Address;
import com.fastcampus.jpa.bookmanager.domain.Gender;
import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@Transactional // 각 테스트가 종료될때마다 데이터들을 다시 롤백해주는 어노테이션이다.
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
//    @Transactional    // saveAll 할때아마 Transactional 이 필요했던거같다.
    void crud(){
        // saveAll , save
//        User user1 = new User("jack" , "jack@fastcampus.com");
////        User user2 = new User("steve" , "steve@fastcampus.com");

//
////        userRepository.saveAll(Lists.newArrayList(user1, user2));
//        userRepository.save(user1);
//        List<User> users = userRepository.findAll();
//
//        users.forEach(System.out::println);


        // getOne
//        User user = userRepository.getOne(1L);
//            // getOne 은 lazypatch 를 지원하고있다
//        System.out.println(user);


        // findById
//        User user = userRepository.findById(1L).orElse(null);
//
//        System.out.println(user);


        // flush
//        userRepository.saveAndFlush(new User("new martin" , "martin@fastcampus.com"));
//
////        userRepository.flush(); // flush 메서드는 쿼리에 변화를 주는것이 아니라
//                                // DB 반영시점을 조절하는 내용이다.
//
//        userRepository.findAll().forEach(System.out::println);


        // count
//        long count = userRepository.count();
//
//        System.out.println(count);


        // exist
//        boolean exists = userRepository.existsById(1L);
//        System.out.println(exists); // count 쿼리가 실행된다.


        //delete
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        // 해당 아이디가 존재하는지 알기위해 내부적으로 select 문으로 해당 아이디가 존재하는지 select 문으로 쿼리문이 실행된다.
//        userRepository.deleteById(1L);
//
//        userRepository.findAll().forEach(System.out::println);
//        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L,3L)));
//        userRepository.findAll().forEach(System.out::println);
            // deleteAll 하면 얘도 select 한번해서 id 값들을 받고 개들을 다 지운다.
            // 출력하면 User 에 아무것도 출력이안된다.

//        userRepository.deleteAllInBatch(userRepository.findAllById(Lists.newArrayList(1L,3L)));
//        userRepository.findAll().forEach(System.out::println);
            // deleteAll 은 delete 문을 하나씩 하나씩 하지만
            // deleteAllInBatch 는
            // delete from user where id = ? or id = ? 문으로 한번에 delete 해서
            // 시간을 줄이게된다.

//        userRepository.deleteAllInBatch();
//        userRepository.findAll().forEach(System.out::println);
            // deleteAllInBatch() 는  그냥 바로 다이렉트로 delete frm user 를 때려버리는 무서운놈이다.


        // paging
//        Page<User> users = userRepository.findAll(PageRequest.of(1, 3));
                                //        totalElements : 5
                                //        totalPages : 2
                                //        numberOfElements : 2 // 현재페이지에 몇개가 있나 2개~
                                //        sort : UNSORTED
                                //        size : 3


//        Page<User> users = userRepository.findAll(PageRequest.of(0,3));
                                //        totalElements : 5
                                //        totalPages : 2
                                //        numberOfElements : 3 // 현재페이지에 몇개가있나 3개~
                                //        sort : UNSORTED
                                //        size : 3

//        System.out.println(users);
                // paging query 문에서 LIMIT OFFSET 이 사용된다.
//        Select * from 테이블명 orders LIMIT 숫자(★) OFFSET 숫자(♥);
//        LIMIT 숫자 : 출력할 행의 수
//        OFFSET 숫자 : 몇번째 row부터 출력할 지. (1번째 row면 0)
//
//        Ex) 10행씩 출력
//        1페이지 : select * from member ORDERS LIMIT 10 OFFSET 0;
//        2페이지 : select * from member ORDERS LIMIT 10 OFFSET 10;

//        System.out.println("page : " + users);
//        System.out.println("totalElements : " + users.getTotalElements());
//        System.out.println("totalPages : " + users.getTotalPages());
//                // 위에서 PageRequest.of(1,3) 을해서 한페이지애 3개가있다.
//                // 따라서 5개의요소만 있으므로 페이지수는 총 2개가 나오게된다.
//
//        System.out.println("numberOfElements : " + users.getNumberOfElements());
//                // 현재 페이지수는 2이다. 즉 4번째 5번째 요소만있다.
//
//        System.out.println("sort : " + users.getSort());
//                // sort 는 적용안햇다.
//
//        System.out.println("size : " + users.getSize());
//                // size 는 3이다.
//
//        users.getContent().forEach(System.out::println);


        // query by Example , query matcher
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("name") // matching 하는데서 무시하겠다. name 은 매칭에서 무시하겟다.
//                .withMatcher("email" , endsWith()); // 이거는 확인하겠다 email 에대해서 확인하겠다.
//
//        Example<User> example = Example.of(new User("ma" , "fastcampus.com"), matcher );
//                            // 여기서는 생성자에 name 필드가 NonNull 이여서
//                            // 넣어줫지만 .withIgnorePaths("name") 으로 무시해줫고
//                            // email 은 endsWith() 로 fastcampus.com 으로 끝나는애들을 조회했다.
//                            // matcher 가 없으면 where user.email = ? , user.name = ? 으로 조회해서
//                            // exact 매취가되게된다. matcher 를 쓰면 like 문으로 조회하므로
//                            // 조회가된다.
//        userRepository.findAll(example).forEach(System.out::println);

//        User user = new User();
//        user.setEmail("slow");
//
//
//        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
//        Example<User> example = Example.of(user, matcher);
//            // slow 단어 검색으로 양방향 검색을 하게된다.
//        userRepository.findAll(example).forEach(System.out::println);
//
                            // LIKE ESCAPE 예제
                    //        WITH TEST_A (NAME) AS (
                    //                SELECT '손%꽁쥐' UNION ALL
                    //                SELECT '손꽁쥐' UNION ALL
                    //                SELECT '손_꽁쥐'
                    //        )
                    //        SELECT *
                    //        FROM TEST_A
                    //        WHERE NAME LIKE '%#%%' ESCAPE '#'
                                // 손%꽁쥐 가 출력된다.


        // update
        userRepository.save(new User("david"  , "david@fastcampus.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@fastcampus.com");

        userRepository.save(user);
            // save 함수는
        // 어떨때는 insert 쿼리를 만들고
        // 새로운 아이면은 insert 를하고

        // 어떨때는 update 쿼리를 만든다.
        // 새로운아이가 아니면 update 를한다.

// 라이브러리 파일을 보면서 공부하면 이보다 좋은 방법은없다!!!!!



        // sort
//        findAll(Sort.by(Sort.Direction.DESC,"name"));

//        List<Long> ids = new ArrayList<>();
//        ids.add(1L);
//        ids.add(3L);
//        ids.add(5L);

//        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L ,5L));
//
//        users.forEach(System.out::println);


//        userRepository.save(new User());
//
//        userRepository.findAll().forEach(System.out::println);


        // userRepository.findAll().forEach(System.out::println); 을 풀어쓰면 아래와 같다.
//        for(User user : userRepository.findAll()){
//            System.out.println(user);
//        }
    }

    @Test
    void select(){

//        System.out.println(userRepository.findByName("dennis"));
//        Hibernate:
    //        select
    //            user0_.id as id1_0_,
    //            user0_.created_at as created_2_0_,
    //            user0_.email as email3_0_,
    //            user0_.name as name4_0_,
    //            user0_.updated_at as updated_5_0_
    //        from
    //            user user0_
    //        where
    //            user0_.name=?
    //        [User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T12:28:25.151342, updatedAt=2021-06-16T12:28:25.151342)]

//        System.out.println("findByEmail : " + userRepository.findByEmail("martin@fastcampus.com"));
//        Hibernate:
    //        select
    //            user0_.id as id1_0_,
    //            user0_.created_at as created_2_0_,
    //            user0_.email as email3_0_,
    //            user0_.name as name4_0_,
    //            user0_.updated_at as updated_5_0_
    //        from
    //            user user0_
    //        where
    //            user0_.email=?
    //        findByEmail : User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:28:25.142366, updatedAt=2021-06-16T12:28:25.142366)

//        System.out.println("getByEmail : " + userRepository.getByEmail("martin@fastcampus.com"));
//        Hibernate:
//            select
//                user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//                user0_.email as email3_0_,
//                user0_.name as name4_0_,
//                user0_.updated_at as updated_5_0_
//            from
//                user user0_
//            where
//                user0_.email=?
//            getByEmail : User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:28:25.142366, updatedAt=2021-06-16T12:28:25.142366)


//        System.out.println("readByEmail : " + userRepository.readByEmail("martin@fastcampus.com"));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.email=?
//        readByEmail : User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:28:25.142366, updatedAt=2021-06-16T12:28:25.142366)

//        System.out.println("queryByEmail : " + userRepository.queryByEmail("martin@fastcampus.com"));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.email=?
//        queryByEmail : User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:28:25.142366, updatedAt=2021-06-16T12:28:25.142366)

//        System.out.println("searchByEmail : " + userRepository.searchByEmail("martin@fastcampus.com"));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.email=?
//        searchByEmail : User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:28:25.142366, updatedAt=2021-06-16T12:28:25.142366)

//        System.out.println("streamByEmail : " + userRepository.streamByEmail("martin@fastcampus.com"));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.email=?
//        streamByEmail : User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:28:25.142366, updatedAt=2021-06-16T12:28:25.142366)

//        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("martin@fastcampus.com"));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.email=?
//        findUserByEmail : User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:28:25.142366, updatedAt=2021-06-16T12:28:25.142366)

//        System.out.println("findSomethingByEmail : " + userRepository.findSomethingByEmail("martin@fastcampus.com"));
//        System.out.println("findByByName : " + userRepository.findByByName("martin@fastcampus.com"));
                    // 오류 발생 해서 테스트를 꼭 해봐라

//        System.out.println("FindFirst1ByName : " + userRepository.findFirst1ByName("martin"));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.name=? limit ?
//        FindFirst1ByName : [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:52:39.453876, updatedAt=2021-06-16T12:52:39.453876)]

//        System.out.println("FindTop1ByName : " + userRepository.findTop1ByName("martin"));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.name=? limit ?
//        FindTop1ByName : [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:52:39.453876, updatedAt=2021-06-16T12:52:39.453876)]

//        System.out.println("FindFirst2ByName : " + userRepository.findFirst2ByName("martin"));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.name=? limit ?
//        FindFirst2ByName : [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:58:03.394934, updatedAt=2021-06-16T12:58:03.394934), User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T12:58:03.400916, updatedAt=2021-06-16T12:58:03.400916)]


//        System.out.println("FindTop2ByName : " + userRepository.findTop2ByName("martin"));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.name=? limit ?
//        FindTop2ByName : [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T12:58:03.394934, updatedAt=2021-06-16T12:58:03.394934), User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T12:58:03.400916, updatedAt=2021-06-16T12:58:03.400916)]

//        System.out.println("FindLast1ByName : " + userRepository.findLast1ByName("martin"));
            // FindLast1ByName 은 이러한 함수가 없어서 spring boot 에서 알아서
            // Last1 을 무시하고 FindByName 함수를 실행한다.

            // 쿼리도 아래와같이 결과값이 두개가 나온다.
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.name=?
//        FindLast1ByName : [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T13:01:55.179255, updatedAt=2021-06-16T13:01:55.179255), User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T13:01:55.187223, updatedAt=2021-06-16T13:01:55.187223)]

        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("martin@fastcampus.com", "dennis") );
            // and 쿼리문은 다음과 같다.
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.email=?
//        and user0_.name=?
//        findByEmailAndName : [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T14:18:26.634222, updatedAt=2021-06-16T14:18:26.634222)]

        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("martin@fastcampus.com", "dennis") );
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.email=?
//        or user0_.name=?
//        findByEmailOrName : [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T14:24:22.620342, updatedAt=2021-06-16T14:24:22.620342), User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T14:24:22.632310, updatedAt=2021-06-16T14:24:22.632310)]


        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)) );
            // query :
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.created_at>?
//                findByCreatedAtAfter :
//                  [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T14:29:04.049706, updatedAt=2021-06-16T14:29:04.049706)
//                , User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T14:29:04.061674, updatedAt=2021-06-16T14:29:04.061674)
//                , User(id=3, name=sophia, email=sophia@slowcampus.com, createdAt=2021-06-16T14:29:04.062672, updatedAt=2021-06-16T14:29:04.062672)
//                , User(id=4, name=james, email=james@slowcampus.com, createdAt=2021-06-16T14:29:04.062672, updatedAt=2021-06-16T14:29:04.062672)
//                , User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T14:29:04.063670, updatedAt=2021-06-16T14:29:04.063670)]

        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.id>?
//                findByIdAfter : [User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T14:32:34.394165, updatedAt=2021-06-16T14:32:34.394165)]

        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.created_at>?
//                findByCreatedAtGreaterThan :
//                [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T14:39:12.916055, updatedAt=2021-06-16T14:39:12.916055)
//                , User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T14:39:12.932098, updatedAt=2021-06-16T14:39:12.932098)
//                , User(id=3, name=sophia, email=sophia@slowcampus.com, createdAt=2021-06-16T14:39:12.932098, updatedAt=2021-06-16T14:39:12.932098)
//                , User(id=4, name=james, email=james@slowcampus.com, createdAt=2021-06-16T14:39:12.933069, updatedAt=2021-06-16T14:39:12.933069)
//                , User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T14:39:12.933069, updatedAt=2021-06-16T14:39:12.933069)]

        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.created_at>=?
//                findByCreatedAtGreaterThanEqual :
//                [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T14:47:24.445606, updatedAt=2021-06-16T14:47:24.445606),
//                User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T14:47:24.452586, updatedAt=2021-06-16T14:47:24.452586), User(id=3, name=sophia, email=sophia@slowcampus.com, createdAt=2021-06-16T14:47:24.453584, updatedAt=2021-06-16T14:47:24.453584), User(id=4, name=james, email=james@slowcampus.com, createdAt=2021-06-16T14:47:24.453584, updatedAt=2021-06-16T14:47:24.453584), User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T14:47:24.453584, updatedAt=2021-06-16T14:47:24.453584)]

        System.out.println("findByCreatedAtBetween : " + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L) , LocalDateTime.now().plusDays(1L)));
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.created_at between ? and ?
//                findByCreatedAtBetween :
//                [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T15:35:13.621431, updatedAt=2021-06-16T15:35:13.621431)
//                , User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T15:35:13.630406, updatedAt=2021-06-16T15:35:13.630406)
//                , User(id=3, name=sophia, email=sophia@slowcampus.com, createdAt=2021-06-16T15:35:13.630406, updatedAt=2021-06-16T15:35:13.630406)
//                , User(id=4, name=james, email=james@slowcampus.com, createdAt=2021-06-16T15:35:13.631402, updatedAt=2021-06-16T15:35:13.631402)
//                , User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T15:35:13.631402, updatedAt=2021-06-16T15:35:13.631402)]



        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L , 3L));
                // Between 은 양끝의 값을 포함한다.
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.id between ? and ?
//                findByIdBetween :
//                [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T15:36:28.733264, updatedAt=2021-06-16T15:36:28.733264)
//                , User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T15:36:28.755742, updatedAt=2021-06-16T15:36:28.755742)
//                , User(id=3, name=sophia, email=sophia@slowcampus.com, createdAt=2021-06-16T15:36:28.758274, updatedAt=2021-06-16T15:36:28.758274)]

        System.out.println("findByIdGreaterThanEqualAndLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L , 3L));

            // findByIdBetween 와 같은 기능이다.
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.id>=?
//        and user0_.id<=?
//                findByIdGreaterThanEqualAndLessThanEqual :
//                [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T15:42:29.222912, updatedAt=2021-06-16T15:42:29.222912)
//                , User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T15:42:29.230892, updatedAt=2021-06-16T15:42:29.230892)
//                , User(id=3, name=sophia, email=sophia@slowcampus.com, createdAt=2021-06-16T15:42:29.230892, updatedAt=2021-06-16T15:42:29.230892)]

        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
//        Hibernate:
//        select
//        user0_.id as id1_0_,
//                user0_.created_at as created_2_0_,
//        user0_.email as email3_0_,
//                user0_.name as name4_0_,
//        user0_.updated_at as updated_5_0_
//                from
//        user user0_
//        where
//        user0_.id is not null
//                findByIdIsNotNull :
//                [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T15:46:47.002131, updatedAt=2021-06-16T15:46:47.002131)
//                , User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T15:46:47.015100, updatedAt=2021-06-16T15:46:47.015100)
//                , User(id=3, name=sophia, email=sophia@slowcampus.com, createdAt=2021-06-16T15:46:47.016095, updatedAt=2021-06-16T15:46:47.016095)
//                , User(id=4, name=james, email=james@slowcampus.com, createdAt=2021-06-16T15:46:47.016095, updatedAt=2021-06-16T15:46:47.016095)
//                , User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T15:46:47.016095, updatedAt=2021-06-16T15:46:47.016095)]

//        System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());

        // query :
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        exists (
//                select
//                address2_.id
//                from
//                user_address address1_,
//                address address2_
//                where
//                user0_.id=address1_.user_id
//                and address1_.address_id=address2_.id
//        )
//        findByAddressIsNotEmpty : []

        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("martin" , "dennis")));

        // or 조건을 쓰는거와 비슷하다.
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        user0_.name in (
//                ? , ?
//        )
//        findByNameIn :
//        [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T16:57:00.572557, updatedAt=2021-06-16T16:57:00.572557)
//        , User(id=2, name=dennis, email=dennis@fastcampus.com, createdAt=2021-06-16T16:57:00.578540, updatedAt=2021-06-16T16:57:00.578540)
//        , User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T16:57:00.580539, updatedAt=2021-06-16T16:57:00.580539)]

        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        user0_.name like ? escape ?
//        findByNameStartingWith :
//        [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T17:04:59.228430, updatedAt=2021-06-16T17:04:59.228430)
//        , User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T17:04:59.237405, updatedAt=2021-06-16T17:04:59.237405)]


        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        user0_.name like ? escape ?
//            findByNameEndingWith :
//            [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T17:04:59.228430, updatedAt=2021-06-16T17:04:59.228430)
//            , User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T17:04:59.237405, updatedAt=2021-06-16T17:04:59.237405)]


        System.out.println("findByNameContains : " + userRepository.findByNameContains("art"));
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        user0_.name like ? escape ?
//                findByNameContains :
//                [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T17:04:59.228430, updatedAt=2021-06-16T17:04:59.228430)
//                , User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T17:04:59.237405, updatedAt=2021-06-16T17:04:59.237405)]

        System.out.println("findByNameLike : " + userRepository.findByNameLike("%art%"));
//        System.out.println("findByNameLike : " + userRepository.findByNameLike("%" + "art" + "%"));
        // 서비스할때는 위와같이 문자를 받아야하는데 가독성이 매우 떨어진다.
            // %art% => Contains 함수와 같다.
            // %tin => EndingWith 함수와 같다.
            // mar% => StartingWith 함수와 같다.

//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        user0_.name like ? escape ?
//                findByNameLike : [User(id=1, name=martin, email=martin@fastcampus.com, createdAt=2021-06-16T17:09:22.759110, updatedAt=2021-06-16T17:09:22.759110), User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-16T17:09:22.766091, updatedAt=2021-06-16T17:09:22.766091)]




    }
    @Test
    void pagingAndSortingTest(){
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("martin"));
//        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("martin"));
                            // Last1 이 무시가되서 findByName 함수가 호출되서 limit 가 없는 쿼리문이 실행된다.

        System.out.println(" findTop1ByNameOrderByIdDesc : " + userRepository.findTop1ByNameOrderByIdDesc("martin"));
        System.out.println(" findTopByNameOrderByIdDesc : " + userRepository.findTopByNameOrderByIdDesc("martin"));
                    // 위두개는 같다 findTop1에서 1 을안쓰면 디폴드로 한개만 찾는 쿼리문이 생성된다.

        System.out.println(" findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        user0_.name=?
//        order by
//        user0_.id desc,
//        user0_.email asc limit ?
//                findFirstByNameOrderByIdDescEmailAsc : [User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-17T16:50:34.657354, updatedAt=2021-06-17T16:50:34.657354)]


        System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("martin" , Sort.by(Sort.Order.desc("id"))));
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        user0_.name=?
//        order by
//        user0_.id desc limit ?
//                findFirstByNameWithSortParams : [User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-17T16:57:26.222494, updatedAt=2021-06-17T16:57:26.222494)]

//        System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("martin" , Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        System.out.println("findFirstByNameWithSortParams : " + userRepository.findFirstByName("martin" , getSort())); // Sorting 함수를 따로 정의해서 사용하는 방법
                            // FindTop1ByNameOrderByIdDescEmailAsc 처럼 함수명에서 다 처리할수도 있고 , 아니면 findFirstByName 기본 함수에서 parameter 로 sorting 정보들을 넣어서 할수도있다.
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        user0_.name=?
//        order by
//        user0_.id desc,
//        user0_.email asc limit ?
//                findFirstByNameWithSortParams : [User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-17T16:59:31.947988, updatedAt=2021-06-17T16:59:31.947988)]

        // 보통은 기본함수를 쓰고 파라미터를 쭉쭉 나열하는식으로 한다 왜냐하면 함수명으로 모든 쿼리문을 처리하면 코드 가독성이 너무 떨어져서 쿼리문이 길어지면 그만큼 남들이 봤을때 무슨 함수인지 알기 힘들기때문이다.
// 결론 : parameter 형식을 추천한다.
        // 같은 결과가 나오더라도 여러가지 방법이 있으므로 적절하게 코드 가독성ㅇ이 최상이 되도록하는 함수를 사용해라


        System.out.println("findByNameWithPaging : " + userRepository.findByName(("martin") , PageRequest.of(1, 1 , Sort.by(Sort.Order.desc("id")))).getTotalElements());
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.name as name4_1_,
//        user0_.updated_at as updated_5_1_
//                from
//        user user0_
//        where
//        user0_.name=?
//        order by
//        user0_.id desc limit ?
//
//                Hibernate:
//                select
//        count(user0_.id) as col_0_0_
//        from
//        user user0_
//        where
//        user0_.name=?
//        findByNameWithPaging : Page 1 of 2 containing com.fastcampus.jpa.bookmanager.domain.User instances
//        findByNameWithPaging : [User(id=5, name=martin, email=martin@another.com, createdAt=2021-06-17T17:29:07.263487, updatedAt=2021-06-17T17:29:07.263487)]



    }

    @Test
    void insertAndUpdateTest(){
        User user = new User();

        user.setName("martin");
        user.setEmail("martin2@fastcampus.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrrtin");

        userRepository.save(user2);

//        Hibernate:
//        call next value for hibernate_sequence
//        Hibernate:
//        insert
//                into
//        user
//                (created_at, email, name, updated_at, id)
//        values
//                (?, ?, ?, ?, ?)
//        Hibernate:
//        select
//        user0_.id as id1_1_0_,
//                user0_.created_at as created_2_1_0_,
//        user0_.email as email3_1_0_,
//                user0_.name as name4_1_0_,
//        user0_.updated_at as updated_5_1_0_
//                from
//        user user0_
//        where
//        user0_.id=?
//        Hibernate:
//        select
//        user0_.id as id1_1_0_,
//                user0_.created_at as created_2_1_0_,
//        user0_.email as email3_1_0_,
//                user0_.name as name4_1_0_,
//        user0_.updated_at as updated_5_1_0_
//                from
//        user user0_
//        where
//        user0_.id=?
//        Hibernate:
//        update
//                user
//        set
//        created_at=?,
//        email=?,
//        name=?,
//        updated_at=?
//        where
//        id=?

    }

    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRowRecord().get("gender"));

    }

    @Test
    void listenerTest(){
        User user = new User();
        user.setEmail("martin2@fastcampus.com");
        user.setName("martin");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("marrrrrrrrrrrrtin");

        userRepository.save(user2);

        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("martin2@fastcampus.com");
        user.setName("martin");
//        user.setCreatedAt(LocalDateTime.now());
//        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("martin2@fastcampus.com"));
    // 전체 테스트를 하게되면 앞에서 했던 test 에서 save 동작이여러개 있어서
    // 데이터들이 서로 섞여서 martin2@fastcampus.com 이라는 이메일을 가진 데이터가 너무많다고
    // 에러가 나는것이다 . test 를 이함수만 돌리면 에러는 안난다.
    }

    @Test
    void preUpdatedTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is : " + user);

        user.setName("martin2");
        userRepository.save(user);

        System.out.println("to-be : " + userRepository.findAll().get(0));

//        Hibernate:
//        select
//        user0_.id as id1_1_0_,
//                user0_.created_at as created_2_1_0_,
//        user0_.email as email3_1_0_,
//                user0_.gender as gender4_1_0_,
//        user0_.name as name5_1_0_,
//                user0_.updated_at as updated_6_1_0_
//        from
//        user user0_
//        where
//        user0_.id=?
//        as-is : User(id=1, name=martin, email=martin@fastcampus.com, gender=null, createdAt=2021-06-18T18:14:24.874452, updatedAt=2021-06-18T18:14:24.874452)
//        Hibernate:
//        select
//        user0_.id as id1_1_0_,
//                user0_.created_at as created_2_1_0_,
//        user0_.email as email3_1_0_,
//                user0_.gender as gender4_1_0_,
//        user0_.name as name5_1_0_,
//                user0_.updated_at as updated_6_1_0_
//        from
//        user user0_
//        where
//        user0_.id=?
//        Hibernate:
//        update
//                user
//        set
//        email=?,
//        gender=?,
//        name=?,
//        updated_at=?
//        where
//        id=?
//        Hibernate:
//        select
//        user0_.id as id1_1_,
//                user0_.created_at as created_2_1_,
//        user0_.email as email3_1_,
//                user0_.gender as gender4_1_,
//        user0_.name as name5_1_,
//                user0_.updated_at as updated_6_1_
//        from
//        user user0_
//        to-be : User(id=1, name=martin2, email=martin@fastcampus.com, gender=null, createdAt=2021-06-18T18:14:24.874452, updatedAt=2021-06-18T18:14:25.386084)


//        createdAt=2021-06-18T18:14:24.874452, updatedAt=2021-06-18T18:14:24.874452

//        createdAt=2021-06-18T18:14:24.874452, updatedAt=2021-06-18T18:14:25.386084

        //          위와 같이 updateAt 시간이 변화가생겼다 listener 를 사용함으로써
    }

    @Test
    void userHistoryTest(){
        User user = new User();
        user.setEmail("martin-newfastcampus.com");
        user.setName("martin-new");
        userRepository.save(user);

        user.setName("martin-new-new");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

//        Hibernate:
//        call next value for hibernate_sequence
//        Hibernate:
//        insert
//                into
//        user
//                (created_at, email, gender, name, updated_at, id)
//        values
//                (?, ?, ?, ?, ?, ?)
//        Hibernate:
//        select
//        user0_.id as id1_2_0_,
//                user0_.created_at as created_2_2_0_,
//        user0_.email as email3_2_0_,
//                user0_.gender as gender4_2_0_,
//        user0_.name as name5_2_0_,
//                user0_.updated_at as updated_6_2_0_
//        from
//        user user0_
//        where
//        user0_.id=?
//        Hibernate:
//        call next value for hibernate_sequence
//        Hibernate:
//        insert
//                into
//        user_history
//                (created_at, email, name, updated_at, user_id, id)
//        values
//                (?, ?, ?, ?, ?, ?)
//        Hibernate:
//        update
//                user
//        set
//        email=?,
//        gender=?,
//        name=?,
//        updated_at=?
//        where
//        id=?
//        Hibernate:
//        select
//        userhistor0_.id as id1_3_,
//                userhistor0_.created_at as created_2_3_,
//        userhistor0_.email as email3_3_,
//                userhistor0_.name as name4_3_,
//        userhistor0_.updated_at as updated_5_3_,
//                userhistor0_.user_id as user_id6_3_
//        from
//        user_history userhistor0_
//        UserHistory(id=7, userId=6, name=martin-new-new, email=martin-newfastcampus.com, createdAt=null, updatedAt=null)



    }

    private Sort getSort(){
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email"),
                Sort.Order.desc("createdAt"),
                Sort.Order.asc("updatedAt")
        );
    }

    @Test
    void userRelationTest(){
        User user = new User();
        user.setName("david");
        user.setEmail("david@fastcampus.com");
        user.setGender(Gender.MALE);

        userRepository.save(user);

        user.setName("daniel");
        userRepository.save(user);

        user.setEmail("daniel@fastcampus.com");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

//        List<UserHistory> result = userHistoryRepository.findByUserId(
//                userRepository.findByEmail("daniel@fastcampus.com").getId());

        List<UserHistory> result = userRepository.findByEmail("daniel@fastcampus.com").getUserHistories();
        // annotation 들을 추가해주면 userHistoryRepository.findByUserId(userRepository.findByEmail("daniel@fastcampus.com).getId());
        // 처럼 UserHistory 에서 굳이 findByUserId를 안해도 바로 getUserHistories 를 정의해주엇기때문에 바로접근할수 있게된다.

        result.forEach(System.out::println);

                // 쿼리문이 아래와 같이 나오는데 우리가 원하는 jpa 에서의 쿼리문이 안나온다
                // ( 보통 관계에서는 join 문이 많이쓰이는데 join 이 나오지않는다.
                // 따라서 annotation 으로 이들의 연관관계를 표현해줄수있다.
//        Hibernate:
//        insert
//                into
//        user
//                (id, created_at, updated_at, email, gender, name)
//        values
//                (null, ?, ?, ?, ?, ?)
//        Hibernate:
//        insert
//                into
//        user_history
//                (id, created_at, updated_at, email, name, user_id)
//        values
//                (null, ?, ?, ?, ?, ?)
//        Hibernate:
//        select
//        user0_.id as id1_3_0_,
//                user0_.created_at as created_2_3_0_,
//        user0_.updated_at as updated_3_3_0_,
//                user0_.email as email4_3_0_,
//        user0_.gender as gender5_3_0_,
//                user0_.name as name6_3_0_
//        from
//        user user0_
//        where
//        user0_.id=?
//        Hibernate:
//        update
//                user
//        set
//        created_at=?,
//        updated_at=?,
//        email=?,
//        gender=?,
//        name=?
//        where
//        id=?
//        Hibernate:
//        insert
//                into
//        user_history
//                (id, created_at, updated_at, email, name, user_id)
//        values
//                (null, ?, ?, ?, ?, ?)
//        Hibernate:
//        select
//        user0_.id as id1_3_0_,
//                user0_.created_at as created_2_3_0_,
//        user0_.updated_at as updated_3_3_0_,
//                user0_.email as email4_3_0_,
//        user0_.gender as gender5_3_0_,
//                user0_.name as name6_3_0_
//        from
//        user user0_
//        where
//        user0_.id=?
//        Hibernate:
//        update
//                user
//        set
//        created_at=?,
//        updated_at=?,
//        email=?,
//        gender=?,
//        name=?
//        where
//        id=?
//        Hibernate:
//        insert
//                into
//        user_history
//                (id, created_at, updated_at, email, name, user_id)
//        values
//                (null, ?, ?, ?, ?, ?)
//        Hibernate:
//        select
//        userhistor0_.id as id1_4_,
//                userhistor0_.created_at as created_2_4_,
//        userhistor0_.updated_at as updated_3_4_,
//                userhistor0_.email as email4_4_,
//        userhistor0_.name as name5_4_,
//                userhistor0_.user_id as user_id6_4_
//        from
//        user_history userhistor0_
//        UserHistory(super=BaseEntity(createdAt=2021-06-20T18:45:51.802024, updatedAt=2021-06-20T18:45:51.802024), id=1, userId=6, name=david, email=david@fastcampus.com)
//        UserHistory(super=BaseEntity(createdAt=2021-06-20T18:45:51.852888, updatedAt=2021-06-20T18:45:51.852888), id=2, userId=6, name=daniel, email=david@fastcampus.com)
//        UserHistory(super=BaseEntity(createdAt=2021-06-20T18:45:51.857876, updatedAt=2021-06-20T18:45:51.857876), id=3, userId=6, name=daniel, email=daniel@fastcampus.com)
//        Hibernate:
//        select
//        user0_.id as id1_3_,
//                user0_.created_at as created_2_3_,
//        user0_.updated_at as updated_3_3_,
//                user0_.email as email4_3_,
//        user0_.gender as gender5_3_,
//                user0_.name as name6_3_
//        from
//        user user0_
//        where
//        user0_.email=?
//        Hibernate:
//        select
//        userhistor0_.id as id1_4_,
//                userhistor0_.created_at as created_2_4_,
//        userhistor0_.updated_at as updated_3_4_,
//                userhistor0_.email as email4_4_,
//        userhistor0_.name as name5_4_,
//                userhistor0_.user_id as user_id6_4_
//        from
//        user_history userhistor0_
//        where
//        userhistor0_.user_id=?
//        UserHistory(super=BaseEntity(createdAt=2021-06-20T18:45:51.802024, updatedAt=2021-06-20T18:45:51.802024), id=1, userId=6, name=david, email=david@fastcampus.com)
//        UserHistory(super=BaseEntity(createdAt=2021-06-20T18:45:51.852888, updatedAt=2021-06-20T18:45:51.852888), id=2, userId=6, name=daniel, email=david@fastcampus.com)
//        UserHistory(super=BaseEntity(createdAt=2021-06-20T18:45:51.857876, updatedAt=2021-06-20T18:45:51.857876), id=3, userId=6, name=daniel, email=daniel@fastcampus.com)

        System.out.println("UserHistory.getUser() : " + userHistoryRepository.findAll().get(0).getUser());


    }


    @Test
    void embedTest(){
        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("steve");
        user.setHomeAddress(new Address("서울시" , "강남구" , "강남대로 364 미왕빌딩" , "06241"));
        user.setCompanyAddress(new Address("서울시", "성동구" , "성수이로 113 제강빌딩" , "04794"));

        userRepository.save(user);

        User user1 = new User();
        user1.setName("joshua");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);

        userRepository.save(user1); // 결과는 아래와 같다. homeAddress, CompanyAddress 객체 자체가 null 이고
//        User(super=BaseEntity(createdAt=2021-07-11T21:15:03.798426, updatedAt=2021-07-11T21:15:03.798426), id=7, name=joshua, email=null, gender=null, homeAddress=null, companyAddress=null)

        User user2 = new User();
        user2.setName("jordan");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());

        userRepository.save(user2); // 결과는 아래와 같다. homeAddress ,CompanyAddress 객체내의 필드 값들이 null  이된다.
//        User(super=BaseEntity(createdAt=2021-07-11T21:15:03.803411700, updatedAt=2021-07-11T21:15:03.803411700), id=8, name=jordan, email=null, gender=null, homeAddress=Address(city=null, district=null, detail=null, zipCode=null), companyAddress=Address(city=null, district=null, detail=null, zipCode=null))
//        embedded 된 객체가 null 인경우 내부의 모든 컬럼이 null 인 것과 동일하게 처리된다고 생각하면 된다.
        entityManager.clear();

        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRowRecord().forEach(a-> System.out.println(a.values()));
    }


}



