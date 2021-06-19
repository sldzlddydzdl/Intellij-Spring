package com.fastcampus.jpa.bookmanager.domain.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

// spring boot 는 기본 listener 들을 제공하고 있다.
public class MyEntityListener {
    @PrePersist
    public void prePersist(Object o){ // 해당 entity 를 받아서 처리해야하므로 파라미터를 꼮 받아야한다.
        if (o instanceof Auditable) {
            ((Auditable) o).setCreatedAt(LocalDateTime.now());
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());

        }
    }

    @PreUpdate
    public void preUpdate(Object o){
        if (o instanceof Auditable) {
            ((Auditable) o).setUpdatedAt(LocalDateTime.now());
        }
    }
}

// 이렇게 MyEntityListener 하나를 만들어 놓으면 Book 이나 User 다른 createdAt updatedAt 이 필요한
// Entity 에 갖다붙일수 있어서 반복적인 코드를 줄일수 있게 된다.
