package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = AuditingEntityListener.class)
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    private Long authorId;

//    private Long publisherId;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    // 중간 테이블을 만들지않기위해서 하는 annotation
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @ToString.Exclude
    private Publisher publisher;

//    @ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

//    public void addAuthor(Author... author){ // Test 에서는 Lists.newArrayList(author1 ,author2) 처럼 구현하고있지만
//                                            // 현업에서는 이렇게 메서드를 구현해서 값들을 넣어준다.
////        this.authors.add(author);
//        Collections.addAll(this.authors, author);
//    }

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors){
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }




    // BookReviewInfo 에
//    @OneToOne(optional = false)
//    private Book book;
//    서로를 optional 속성으로 mappedBy 와 optional false  를 걸어줘서 1 대 1 relation 을 가지게해줘서
//    지금 Book 과 BookReviewInfo  서로 볼수 있게 했다.

//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;
//
//    @PrePersist
//    public void prePersist(){
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        this.updatedAt = LocalDateTime.now();
//    }
//

    // 거의 모든 Entity 에서 createdAt, updatedAt 필드는 거의 대부분 추가된다.

}
