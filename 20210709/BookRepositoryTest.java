package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.Publisher;
import com.fastcampus.jpa.bookmanager.domain.Review;
import com.fastcampus.jpa.bookmanager.domain.User;
import com.fastcampus.jpa.bookmanager.repository.dto.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("Jpa 초격차 패키지");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        bookRepository.save(book);
        System.out.println(bookRepository.findAll());

//            create table book (
//                    id bigint not null,
//                    created_at timestamp,
//                    updated_at timestamp,
//                    author_id bigint,
//                    category varchar(255),
//                    name varchar(255),
//                    publisher_id bigint,
//                    primary key (id)
//        )
    }

    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();

        User user = userRepository.findByEmail("martin@fastcampus.com");

        System.out.println("Review : " + user.getReviews());
        System.out.println("Book : " + user.getReviews().get(0).getBook());
        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());


    }

    @Test
//    @Transactional
    void bookCascadeTest(){
        Book book = new Book();
        book.setName("JPA 초격차 패키지");

//        bookRepository.save(book); // 영속성 처리

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

//        publisherRepository.save(publisher);

        book.setPublisher(publisher);
//        publisher.getBooks().add(book);
        bookRepository.save(book);

//        publisher.addBook(book); // 가독성을위해 클래스안에 함수를 따로 만들어서 가독성을 늘린다.
//        publisherRepository.save(publisher);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());
//        JPA 는 자바코드를 SQL 코드로 번역해주는 ORM 이다.
//        위 문구는 SQL 과 JAVA 가 섞여있다.
//        set 하고나서 save 하고 , set 하고나서 save 하고 이를 반복하는게
//        뭔가좀 안맞고 불필요해보인다. 그래서 이를 해결하기위한게 Cascade 이다.

        // merge 에대한 event 가 발생한다. 그런데 우리는 persist 에 대해서만 cascade 해줘서
        // merge 에대한 영속성 전이가 일어나지 않았다.
//        @ManyToOne(cascade = { CascadeType.PERSIST , CascadeType.MERGE} )
        // 따라서 위처럼 CascadeType.MERGE 를 설정해주면 영속성 전이가 일어난다.

//        public enum CascadeType {
//
//            /** Cascade all operations */
//            ALL,
//
//            /** Cascade persist operation */
//            PERSIST,
//
//            /** Cascade merge operation */
//            MERGE,
//
//            /** Cascade remove operation */
//            REMOVE,
//
//            /** Cascade refresh operation */
//            REFRESH, -> entity 를 다시 로딩했을때 연관관계 가 있는 entity
//
//            /**
//              Cascade detach operation
//
//            since 2.0
//            DETACH   -> 영속성을 관리하지 않겠다.
//        }

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("슬로우캠퍼스");

        bookRepository.save(book1);

        System.out.println("publisher : " + publisherRepository.findAll());

        Book book2 = bookRepository.findById(1L).get();
//        bookRepository.delete(book2);

//        publisherRepository.delete(book2.getPublisher()); // 이렇게 일일이 지울수 있지만
        // CascadeType.REMOVE , Cascade.PERSIST, Cascade.MERGE  를 해주면  영속성전이로
        // 위코드를 안써줘도 연관된 publisher 가 지워진다.

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);
    // setter 를 통해서 null 을주게되면 연관관계를 제거하게된다 -> 고아제거

        bookRepository.save(book3);

        System.out.println("books : "  + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());
        System.out.println("book3-publisher : " + bookRepository.findById(1L).get().getPublisher());

    }

    @Test
    void bookRemoveCascadeTest(){
        bookRepository.deleteById(1L);

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publishers : " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
        // data.sql 은 단순히 쿼리문만 실행하므로 이전에 우리가 만들었던 listener 라던지 이런것들이 타지않아서
        // createAt 과 updateAt 은 null 이 되는것을 꼭 인지해라.
    }

    @Test
    void softDelete(){
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));

//        bookRepository.findByCategoryIsNull().forEach(System.out::println);
//        Book(super=BaseEntity(createdAt=null, updatedAt=null), id=1, name=JPA 초격차 패키치, category=null, authorId=null, deleted=false)
//        Book(super=BaseEntity(createdAt=null, updatedAt=null), id=2, name=Spring Security 초격차 패키지, category=null, authorId=null, deleted=false)
//        Book(super=BaseEntity(createdAt=null, updatedAt=null), id=3, name=SpringBoot 올인원 패키지, category=null, authorId=null, deleted=true)

        ///// 위는 deleted 가 false 만 데이터가 나와야되는데 true 까지 다나오고 밑에는 deleted 가 true 이면 데이터가 안나오게되므로 아래와 같이나와야한다.

//        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
//        Book(super=BaseEntity(createdAt=null, updatedAt=null), id=1, name=JPA 초격차 패키치, category=null, authorId=null, deleted=false)
//        Book(super=BaseEntity(createdAt=null, updatedAt=null), id=2, name=Spring Security 초격차 패키지, category=null, authorId=null, deleted=false)
//        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);
//        Book(super=BaseEntity(createdAt=null, updatedAt=null), id=1, name=JPA 초격차 패키치, category=null, authorId=null, deleted=false)
//        Book(super=BaseEntity(createdAt=null, updatedAt=null), id=2, name=Spring Security 초격차 패키지, category=null, authorId=null, deleted=false)
    }

    @Test
    void queryTest(){
        bookRepository.findAll().forEach(System.out::println);
        System.out.println("findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual : " +
                bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                        "JPA 초격차 패키지",
                        LocalDateTime.now().minusDays(1L),
                        LocalDateTime.now().minusDays(1L)
                ));

        // 쿼리문이 아래와같이 나온다.
//        Hibernate:
//        select
//        book0_.id as id1_2_,
//                book0_.created_at as created_2_2_,
//        book0_.updated_at as updated_3_2_,
//                book0_.author_id as author_i4_2_,
//        book0_.category as category5_2_,
//                book0_.deleted as deleted6_2_,
//        book0_.name as name7_2_,
//                book0_.publisher_id as publishe8_2_
//        from
//        book book0_
//        where
//                (
//                        book0_.deleted = 0
//                )
//        and (
//                book0_.category is null
//        )
//        and book0_.name=?
//        and book0_.created_at>=?
//                and book0_.updated_at>=?
//                findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual : []

        System.out.println("findByNameRecently : " + bookRepository.findByNameRecently("JPA 초격차 패키지" , LocalDateTime.now().minusDays(1L) , LocalDateTime.now().minusDays(1L)));

//        bookRepository.findBookNameAndCategory().forEach(tuple -> {System.out.println(tuple.get(0) + " : " + tuple.get(1));});

        bookRepository.findBookNameAndCategory().forEach(tuple -> {System.out.println(tuple.getName() + " : " + tuple.getCategory());});

        bookRepository.findBookNameAndCategory(PageRequest.of(1  , 1)).forEach(
                bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory()));


        bookRepository.findBookNameAndCategory(PageRequest.of(0  , 1)).forEach(
                bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory()));

    }

    @Test
    void nativeQueryTest(){
//        bookRepository.findAll().forEach(System.out::println); // jpaRepository 에 존재하는 findAll 을 시행하는 쿼리이고
//        bookRepository.findAllCustom().forEach(System.out::println); // 우리가 만든 native 쿼리를 실행하는 것이다.

        List<Book> books = bookRepository.findAll();

        for(Book book : books){
            book.setCategory("IT전문서");
        }

        bookRepository.saveAll(books);

        System.out.println(bookRepository.findAll());

        System.out.println("affected rows : " + bookRepository.updateCategories());
        bookRepository.findAllCustom().forEach(System.out::println);

        System.out.println(bookRepository.showTables()); // test db 에서 autoddl 을 했으므로 tables 가 나오게된다.
    }


    @Test
    void converterTest(){
        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("또다른 IT전문서적");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);

        System.out.println(bookRepository.findRowRecord().values());

        bookRepository.findAll().forEach(System.out::println);


    }


    private void givenBookAndReview(){
        givenReview(givenUser(),givenBook(givenPublisher()));
    }

    private User givenUser(){
        return userRepository.findByEmail("martin@fastcampus.com");
    }

    private Book givenBook(Publisher publisher){
        Book book = new Book();

        book.setName("Jpa 초격차 패키지");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private void givenReview(User user , Book book){
        Review review = new Review();
        review.setTitle("내 인생을 바꾼 책");
        review.setContent("너무너무 재미있고 즐거운 책이었어요.");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        return publisherRepository.save(publisher);
    }
}
