package com.fastcampus.jpa.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true) // 상속받는 super class 의 정보를 toString 에 포함하도록 지정해주는 값이다.
                            // default 값이 false 여서 true 로 해준다
@EqualsAndHashCode(callSuper = true)

// @ToString 과 @EqualsAndHashCode 을 true 해줌으로써
// id=6, bookId=1, averageReviewScore=4.5, reviewCount=2) created_at 과 updated_at 이 출력이안됬는데 출력이 아래처럼된다.
//[BookReviewInfo(super=BaseEntity(createdAt=2021-06-19T17:45:05.310233, updatedAt=2021-06-19T17:45:05.310233), id=6, bookId=1, averageReviewScore=4.5, reviewCount=2)]


public class BookReviewInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false /*, mappedBy = "bookReviewInfo"*/) // 1대1  로 연관관계를 맵핑한다는 의미이다.
                // optional = false 은 반드시 존재하는 값이되는 것이다.
                // book 은 절대 null 을 허용하지않겠다는 의미이다.
                // mappedBy 을 설정하게되면 연관키를 해당테이블에서는 더이상 가지지않게된다.
    private Book book;

//    private Long bookId;

    private float averageReviewScore;

    private int reviewCount; // default 값을 0으로하기위해서 Integer 로 안햇다
                            // Integer 로 하게되면 default 값이 NULL 이되므로 NULL 을 체크해줘야하는 상황이생ㄱ니다.

//    create table book_review_info (
//            id bigint not null,
//            created_at timestamp,
//            updated_at timestamp,
//            average_review_score float not null, // float 으로 함으로써 query 문에서 알아서 NotNull 을 선언해준다.
//            book_id bigint,
//            review_count integer not null, // int 으로 함으로써 query 문에서 알아서 NotNull 을 선언해준다.
//            primary key (id)
//    )


}
