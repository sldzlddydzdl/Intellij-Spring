package com.fastcampus.jpa.bookmanager.domain.listener;

import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.domain.UserHistory;
import com.fastcampus.jpa.bookmanager.repository.UserHistoryRepository;
import com.fastcampus.jpa.bookmanager.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

//@Component
public class UserEntityListener {

//    @Autowired
//    private UserHistoryRepository userHistoryRepository;

//    @PrePersist // 둘다 적용할수 있따.
//    @PreUpdate //
    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){

        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;
        UserHistory userHistory = new UserHistory(); // history 는 insert 해야하므로 새로운객체 생성
//        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);

        userHistoryRepository.save(userHistory);
    }

}
