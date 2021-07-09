package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value = "update book set category='none'", nativeQuery = true)
    void update(); // 이메서드를 실행하면 category 를 none 으로세팅해준다.

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse(); //DeletedFalse 를 항상 붙여야되고
                                                        // 실수로 DeletedFalse 가 누락되면 탈퇴한 회원의 정보가 보여지게되고 탈퇴한 회원이 접속할수있게 되는 이런 사이드 이펙트가 발생한다.
                                                        // 이런 실수를 방지하기위해 Book 위에 @Where(clause = "deleted = false") 를 써주면 모든 쿼리마다 where 조건을 걸어주게된다.

    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name , LocalDateTime createdAt , LocalDateTime updatedAt);
    // Category 는 null 이면서 이름은 우리가 준 name 과같은거 그리고 createdAt 보다 크거나 같은것 updatedAt 보다 크거나 같은것을 추출 하는 로직이다.
    // 이렇게 긴이름의 메서드가 도대체 어떤 역할을 하는지 where 조건을 봐서는 가독성이 너무 떨어져서 한눈에 알아볼수가없다.

//    @Query(value = "select b from Book b " +
//            "where name = ?1 and createdAt >= ?2 and updatedAt >= ?3 and category is null")
        // 위같은것을 Jpql 이라고 한다. Book 은  entity 로 처리한다. 하지만 실제 쿼리문을 보게되면 book 으로나온다.
    // createdAt 도 entity 에 있는 이름으로 하고 실제 쿼리는 created_at 이 나온다.
    // jpql 의 장점은 dialect(방언) 을 통해서 database 종류에 따라 서로다른 쿼리를 자동으로 생성을 해준다.
    // 위같은 경우는 우리가 쿼리메소드로 만드는 과정과 비슷한 과정으로 생성된다.

    // ?1 ?2 ?3 을 사용해서 해당 메서드에서 몇뻔째 파라미터인지 확인해서 이 값에 치환을 해준다.
    // 하지만 자바는 순서에 의해서 결정되는 파라미터 결정은 지향하고있다. 안쓰는것을 추천하고있다.
    // 중간에 파라미터가 추가되면 발생할수 있는 side effect 의 확률이높아지므로
    // 이를 개선한 방법이 @Param 이다.
    // @Query annotation 을 사용하는 이유는 큰이유는
    // entity 의 연결되지 않은 쿼리가 가능하다는 것이다.
    // 사실 우리가 예제로 만든 book 이라는 entity 는 그리 많은 필드를 가지고 있지 않다.
    // 하지만 실제 현업에서 사용하게될 database 는 정말 많은 column 들을 가지고 있다.
    // 그럴때 필요한 column 만 추려서 조회를 할수 있게 된다.

    @Query(value = "select b from Book b " +
            "where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt and category is null")
    List<Book> findByNameRecently(
            @Param("name") String name ,
            @Param("createdAt") LocalDateTime createdAt ,
            @Param("updatedAt") LocalDateTime updatedAt);

    @Query(value = "select new com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory(b.name , b.category)  from Book b")
    // jpql 은 Book 이라는 것도 table 이 아니라 java 에서 사용하는 entity 클래스 이다.
    // 이렇게 jpql 은 자바 객체를활용 할 수 있는 sql 구문이기 때문에 그렇기 때문에 new BookNameAndCategory 는 문자열이므로 full package name 을 써줘야 객체를 인식한다.
    List<BookNameAndCategory> findBookNameAndCategory();

    @Query(value = "select new com.fastcampus.jpa.bookmanager.repository.dto.BookNameAndCategory(b.name , b.category)  from Book b")
    Page<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);
    // 이름은 파라미터가 다르면 messaging overloading 이라한다.

    @Query(value = "select * from book" , nativeQuery = true)
    // native query 는 jpql 과 다르게 entity 의 클래스와 필드내임을 그대로 쓰지 않는다.
    // 위처럼 Book 대신 table 이름인 book 을 넣게 된다.
    // Book 에 annotation 에 @where(deleted = false) 가 안먹히고 진짜 위에 쿼리문만 실행해준다.
    // 즉 db 에서 사용하는 sql 쿼리를 그대로 사용하게 된다.
    // 테스트 코드 경우에는 h2 디비를 쓰는게 일반적이다.
    // 실제 product 코드 jpa 쿼리는 동일하다. h2 디비에서 특정디비에 대한 포함하하고 있다.
    // native 쿼리는 디비가 달라지거나 os 가 달라지면 쿼리문이 잘안먹히게 되서 사용을 조금해야된다.
    // 그래도 native 쿼리를 쓰는 이유를 알아보자
    // 1. 성능의 문제를 해결하는데 native 쿼리를 사용한다.
    //
    List<Book> findAllCustom();

    @Modifying // @Modifying 을 안해주면 에러가 난다 @Modifying 을 해줌으로써 update 구문인것을 알려줘야 에러가안난다.
    @Transactional // update delete 는 직접 Transactional 을 선언을 해줘야한다.
    @Query(value = "update book set category = 'IT전문서'" , nativeQuery = true)
    int updateCategories();

    // @Transactional 은 interface 보다는 구체클래스에 쓰라고 정식문서에 나와있따.
    // interface 경우 에는 proxies 만 쓰라고 되있다. 그래도 여기같으경우는 interface 에 사용해도된다.

    @Query(value = "show tables" , nativeQuery = true)// natvieQuery 로는 이러 show tables 도 된다.
    List<String> showTables();

    @Query(value = "select * from book order by id desc limit 1" , nativeQuery = true)
    Map<String, String> findRowRecord();

}
