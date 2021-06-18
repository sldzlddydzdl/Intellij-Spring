package com.fastcampus.jpa.bookmanager.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// 지금거의 모든 Entity 에 @CreatedDate , @LastModifiedDate 가 반복되고있어서
// 반복적인 코드를 줄이기 위해서 BaseEntity 를 만들어주었다

@Data
@MappedSuperclass // BaseEntity 에 가장 필요한 Annotation 이다.
                    // 해당클레스에 필드를 상속받는 entity 에 컬럼으로 포함시켜주겠다는 것이다.
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}
