package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put(){
        User user = new User();

        // dirty check
        user.setName("newUser");
        user.setEmail("newUser@fastcampus.com"); // user 는 비영속상태이다
                                                // 즉 디비에 연동된것이아니라
                                                // 순수히 자바 객체로만 있게된다.
                                                // 가비지 컬렉터를 통해서 사라지는 데이터가된다.

//        userRepository.save(user); 이렇게 해도 EntityManager 가 save 함수에 선언되있어서
                                    // 상관없다.

        entityManager.persist(user);
                        // persist 단어뜻 자체는 지속이라는 뜻
                        // 따라서 이렇게해주면 영속성을 부여해주는 것이다.
                        // 즉 managed 상태가된다.
//        entityManager.detach(user);
//        얼래 영속화되었던 객체를 detach 즉 분리해서 영속성컨텍스트 밖으로 꺼내는 동작이다.
        // 따라서 entityManager.detach(user) 하는 순간 영속성에서 제외되어서
        // newUserAfterPersist 가 적용되지 않는다.

//        close , clear 는 detach 보다더 파괴적인 느낌이다. // 예약애들도 모두 드랍한다.

        user.setName("newUserAfterPersist"); // 에는 다시 디비에 데이터를
        // 맞춰준다 우리가 다시 save 를 호출하지 않아도 디비 데이터와의 정확성을 맞춰준다.
        entityManager.merge(user);
//        userRepository.save(user);
//        entityManager.flush();
//        entityManager.clear();

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);

        // 위에서 remove 햇으므로 에러난다.
//        user1.setName("marrrrrrrrrrrtin");
//        entityManager.merge(user1);

        // 주문과 결제가 하나의 transaction 안에서 해결한다.

    }
}
