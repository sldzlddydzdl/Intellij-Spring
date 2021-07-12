package com.fastcampus.jpa.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY) // 해당 객체를 get set 하지않는 이상 쿼리가 발생하지않는다.
    @ToString.Exclude
    private Book book;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private List<Comment> comments;

    // ManyToOne , One to One 은 기본적으로 FetchType 은 EAGER 이고
    // ManyToMany , OneToMany 는 기본적으로 FetchType 이 LAZY 이다.

}
