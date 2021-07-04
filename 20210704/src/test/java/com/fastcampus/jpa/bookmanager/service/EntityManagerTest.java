package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void entityManagerTest(){
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
//        UserRepository.findAll(); 과 똑같은 테스트이다.
    }

    @Test
    void cacheFindTest(){
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com")); // 1차 캐쉬 적용 됨으로써 jpa select 가 더용이 해진다.
//        System.out.println(userRepository.findById(2L).get()); // 위에서 조회햇으므로 쿼리문은 안나온다.
//        System.out.println(userRepository.findById(2L).get());
//        System.out.println(userRepository.findById(2L).get());
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));
//        System.out.println(userRepository.findByEmail("martin@fastcampus.com"));

        userRepository.deleteById(1L); // delete 나 update 는 일단 조회를 한번거치게된다.
    }

    @Test
    void cacheFindTest2(){
        User user = userRepository.findById(1L).get();
        user.setName("marrrrtin");

        userRepository.save(user);

//        userRepository.flush();

        System.out.println("-----------------------------------");

        user.setEmail("marrrrrrrtin@fastcampus.com");

        userRepository.save(user);

//        userRepository.flush();


//        flush 뜻은 모여있는걸 비워낸다는 의미다.
//        즉 영속성 컨택스트에 쌓여있는 데이터는 엔티티 메니저가 자체적으로 디비에 영속화를 해주지만 사실은 개발자가 의도한 타임에 디비영속화 가 이러우지지안는ㄴ다
//        개발자가 원하는시간에 디비에 영속화를 시켜주기 위해서는 flush 라는 메소드를 사용하게된다.
//        영속성 캐쉬에 쌓여서 아직 반영되지 않은 엔티티의 변경ㅇ을 해당 메서드 실행시점에 모두 디비에 반영해주는 그런 역할을 한다.
//        그렇다고 오히려 flush 를 난발하게 되면 오히려 영속성 캐쉬의 장점을 모두 무효화하는 역할을 하니 적당하게 잘사용하는 것이 중요하다.
//        그리고 flush 로 인해서 예상치못한 동작들이 조그 ㅁ생긴다.

        // 영속성 캐쉬가 어렵다. 개발자가 .save 를 해도 특정시점에 아직 디비에 반영되지않는 경우가 많이 발생하기때문이다.
        // flush 메서드가 호출되는 경우에 개발자가 의도적으로 영속성 캐쉬를디비에 반영하는 액션이다.
        // 그러면 영속성 컨텍스트의 데이터와 실제 디비값이 서로 동기화가 된다.

//        System.out.println(">>> 1 : " +userRepository.findById(1L).get());
//
////        userRepository.flush(); // 의도적으로 영속성 캐쉬를 데이터베이스에 동기화하는 처리를한다.
//
//        System.out.println(">>> 2 : " + userRepository.findById(1L).get());

        System.out.println(userRepository.findAll());   // select * from user

        // update 을 가지고있다가 한번에 update 를 하게된다.
    }
}

// 결론적으로 영속성캐쉬가 flush 가 되서 디비에 반영되는것은
// 1번째 flush 메소드를 명시적으로 호출 하는 시점
// 2번째 transaction 이 끝나서 해당 쿼리가 commit 되는 시점
// 3번째 복잡한 조회 조건에 jpql 쿼리가 실행되는 시점


