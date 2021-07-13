package com.fastcampus.jpa.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // entity 객체라는 것을 표시
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicInsert // insert 시점에  다이나믹 하게 정의를 하게된다.
@DynamicUpdate // 우리가 영향을 받은 컬럼에 대에서만 처리를 해주게된다
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @ToString.Exclude
    private Review review;

    @Column(columnDefinition = "datetime(6) default now(6)")
    // default 값을 지정을 해주면 commentedAt 값을 set 해주지 않으면 자동으로 db 에서 자동으로 현재시간의 timestamp 값을 넣게된다.
    private LocalDateTime commentedAt;
}
