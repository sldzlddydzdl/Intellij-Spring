package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookReviewInfoRepositoryTest {
    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    void crud(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setBook(givenBook()); // null 이여서 에러나므로 강제로 북을 넣어준다.
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>>" + bookReviewInfoRepository.findAll());

    }

    @Test
    void crudTest2(){
//        givenBook();
        givenBookReviewInfo();

        Book result = bookReviewInfoRepository
                        .findById(1L)
                        .orElseThrow(RuntimeException::new)
                        .getBook();

        System.out.println(">>>  " + result);

        BookReviewInfo result2 = bookRepository
                .findById(7L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();

        System.out.println(">>> : " + result2);

//        Hibernate:
//        insert
//                into
//        book
//                (id, created_at, updated_at, author_id, category, name, publisher_id)
//        values
//                (null, ?, ?, ?, ?, ?, ?)
//        Hibernate:
//        insert
//                into
//        book
//                (id, created_at, updated_at, author_id, category, name, publisher_id)
//        values
//                (null, ?, ?, ?, ?, ?, ?)
//        Hibernate:
//        insert
//                into
//        book_review_info
//                (id, created_at, updated_at, average_review_score, book_id, review_count)
//        values
//                (null, ?, ?, ?, ?, ?)
//        Hibernate:
//        select
//        bookreview0_.id as id1_2_,
//                bookreview0_.created_at as created_2_2_,
//        bookreview0_.updated_at as updated_3_2_,
//                bookreview0_.average_review_score as average_4_2_,
//        bookreview0_.book_id as book_id6_2_,
//                bookreview0_.review_count as review_c5_2_
//        from
//        book_review_info bookreview0_
//        Hibernate:
//        select
//        book0_.id as id1_1_0_,
//                book0_.created_at as created_2_1_0_,
//        book0_.updated_at as updated_3_1_0_,
//                book0_.author_id as author_i4_1_0_,
//        book0_.category as category5_1_0_,
//                book0_.name as name6_1_0_,
//        book0_.publisher_id as publishe7_1_0_
//                from
//        book book0_
//        where
//        book0_.id=?
//>>> : [BookReviewInfo(super=BaseEntity(createdAt=2021-06-19T19:12:45.175598, updatedAt=2021-06-19T19:12:45.175598), id=1, book=Book(super=BaseEntity(createdAt=2021-06-19T19:12:45.168616, updatedAt=2021-06-19T19:12:45.168616), id=2, name=Jpa 초격차 패키지, category=null, authorId=1, publisherId=1), averageReviewScore=4.5, reviewCount=2)]
//        Hibernate:
//        select
//        bookreview0_.id as id1_2_0_,
//                bookreview0_.created_at as created_2_2_0_,
//        bookreview0_.updated_at as updated_3_2_0_,
//                bookreview0_.average_review_score as average_4_2_0_,
//        bookreview0_.book_id as book_id6_2_0_,
//                bookreview0_.review_count as review_c5_2_0_,
//        book1_.id as id1_1_1_,
//                book1_.created_at as created_2_1_1_,
//        book1_.updated_at as updated_3_1_1_,
//                book1_.author_id as author_i4_1_1_,
//        book1_.category as category5_1_1_,
//                book1_.name as name6_1_1_,
//        book1_.publisher_id as publishe7_1_1_
//                from
//        book_review_info bookreview0_
//        left outer join     BookReviewInfo 에서 Book 필드를 @OnetoOne(optional = false) 을 해주게되면 반드시 존재하는 경우만 쿼리하므로 inner join 으로 바뀌게된다.
//        book book1_
//        on bookreview0_.book_id=book1_.id
//        where
//        bookreview0_.id=?
//>>>  Book(super=BaseEntity(createdAt=2021-06-19T19:12:45.168616, updatedAt=2021-06-19T19:12:45.168616), id=2, name=Jpa 초격차 패키지, category=null, authorId=1, publisherId=1)

    }

    private Book givenBook(){
        Book book = new Book();
        book.setName("Jpa 초격차 패키지");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        return bookRepository.save(book);

//        System.out.println(">>>  " + bookRepository.findAll());

    }

    private void givenBookReviewInfo(){
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> : " + bookReviewInfoRepository.findAll());

    }

}