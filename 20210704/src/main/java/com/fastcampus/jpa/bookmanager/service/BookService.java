package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class BookService {

    private final AuthorService authorService;

//    @Autowired
    private final BookRepository bookRepository;

//    @Autowired
    private final AuthorRepository authorRepository;

    private final EntityManager entityManager;
//    public void put(){
//        this.putBookAndAuthor();
//    }

    // 이미 put 에서 bean 으로 등록되서 들어간다음 그안에있는 Transactional 메서드를 다시들어가게되면
    // 여기에서는 putBookAndAuthor 의 @Transactional 어노테이션이 무효화가 되므로
    // RuntimeException 임에도 불구하고 rollback 이 안되고 commit 되게 된다.
    // public 이거나 private 이거나 없어도 해당된다.

//    @Transactional/*(rollbackFor = Exception.class)*/ // import 가 두개가있는데 두개 아무거나 써도 Transactional 기능은 다 구현해준다.
    @Transactional(propagation = Propagation.REQUIRED)
    /*public*/public void putBookAndAuthor() /*throws Exception */{
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        try { // try catch 를 안해주면 한군데서만 exception 이 발생하는것이 아니라 여러군대에서 exception 이 발생한것처럼 처리되므로
                // try catch 를 묶어보자
             authorService.putAuthor();
        }catch (RuntimeException e){
            // bookRepository 은 transaction propagation 이 REQUIRED 로 걸려있고
            // authorService.putAuthor(); 도 putAuthor() 에 들어가보면 Transaction propagation 이 REQUIRED 로 걸려있다
            // 그래서 이런경우는 둘다 commit 이 되던가 둘다 rollback 이 되어야하는데 putAuthor 에서 강제로 에러를 내서 rollback 시켯으니
            // book 도 마찬가지로 rollback 이된다. 무지성으로 예상한 결과는 book 은 commit 이 되고 author 는 rollback 이 되는것이였는데
            // 결과는 둘다 rollback 이 됬다.
        }

//        Author author = new Author();
//        author.setName("martin");
//
//        authorRepository.save(author);
//
////        throw new Exception("오류가 나서 DB commit 이 발생하지 않습니다");
        throw new RuntimeException("오류가 발생 하였습니다. Transaction 은 어떻게 될까요");
            // propagation = Propagation.REQUIRED 에서는 오류가 났을때 rollback 해준다.



        // Transactional 에서 exception 이 발생하면 함수가 끝나도 db에 반영되지 않게된다.
        // commit 이 되지않고 rollback 이 되게된다.
    }

//    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get(Long id){
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());
        // transaction 걸려서 mysql 에서 category 를 none 으로 update 해도 반영되지않아서
        //  출력하게되면 그대로 category 는 NULL 을 유지한다.

        entityManager.clear(); // 캐쉬 문제를 해결해주기위해 clear

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();
        // 첫번째 와 마찬가지로
        // transaction 걸려서 mysql 에서 category 를 none 으로 update 해도 반영되지않아서
        //  출력하게되면 그대로 category 는 NULL 을 유지한다.

//        Book book = bookRepository.findById(id).get();
//        book.setName("바뀔까?");
//        bookRepository.save(book);



//        update
//            book
//        set
//            created_at=?,
//            updated_at=?,
//            author_id=?,
//            category=?,
//            name=?,
//            publisher_id=?
//        where
//            id=?
            // set 을 했기때문에 강제로 update 가 된것이다. 위의 query 문이 생성되어서.

                            // read uncommitted
        // Transaction 을기다리고있는 것이다.
        // 중간에 rock 때문에 잠시 멈춰있다가
        // mysql 에서 commit 을 쳐주면 다음 단계로 들어가게된다.
        // 문제점은 이 함수가 끝날때 rollback 을 했음에도 불구하고
        // 더진행하게되면 BookServiceTest 에서 rollback 되서 book 의 name 이 바뀔까 가 되면 안되는데
        // rollback 을 했음에도 불구하고 값이 바뀐값이 그대로 출력되는 문제점이 발생한다.
        // 이를 해결하기위서는 해당 Entity 에 어노테이션으로 @DynamicUpdate 을 달아주면된다.
        // Book 에 @DynamicUpdate
        // 필요한 정보만 update 하기 때문에 category 가 update 되는 현상은 막게된다.
        // 여기서 중요하것은 dirty read 가 발생하여 데이터가 중간에 틀어지는경우가 존재하게되었다.
        // 이런케이스는 대부분 데이터의 정합성을 해치게되는 경우가 많아서 실제 서비스에서는 read uncommitted
        // 격리수준은 일반적으로는 많이 사용하지 않는다.



                            // read committed
        // 그래서 dirty read 현상을 개선하기위한 격리수준이 read committed 속성이다.
        // commit 된 데이터만 읽어오겠다는 것이 read committed 이다.
        // read committed 는의도한대로 읽을수 있게된다.



                            // repeatable_read
        // 한 Transaction 에서 반복적으로 조회를 했을때 그값이 변경될수 있는 현상을 unrepeatable read 상태라 하고
        // 이 unrepeatable read 해결하기위해서 나오는 격리수준이 repeatable read 이다.
        // @Transaction(isolation = Isolation.REPEATABLE_READ) 상태로 하면
        // Transaction 안에서 반복해서 값을 조회하더라도 항상 동일한 값이 리턴되는것을 보장해준다.
        // 심지어 다른 transaction 에서 commit 된값
        // 이 test 처럼 category 가 none 으로 update 됬고 commit 된 값이 발생하더라도
        // REPEATABLE_READ 에서는 별도의 SNAPSHOT 이라해서 commit 데이터를 직접가져오는것이아니라 자기 transaction 이 시작될때에
        // 조회했던 데이터를 별도로저장하고있다가 이 transaction 이 끝나기전까지는 그 SNAPSHOT 정보를 계속적으로 리턴해주게된다.



                            // phantom read
        // unrepeatable read 에서 생기는 문제점이 phantom read 이다.
        // select 를 안하고 update 를 하기위해서 BookRepository 에

        // @Modifying
        // @Query(value = "update book set category='none'", nativeQuery = true)
        // void update(); // 이메서드를 실행하면 category 를 none 으로세팅해준다.
        // mysql 에서 직접 insert 를 해서 데이터 하나를 넣어준다.
        // 위의 과정을 실행해주고 bookRepository.update() 을 실행시키면
        // 우리가 기대하는값은 하나의 데이터만 category 가 none 으로 바뀌어야하는데
        // 두개다 none 으로 바뀌게되는 상황이 발생하는데 이렇게 경우에따라서는 데이터가 안보이는데 처리가 되기도 한다. 이러한 상황을 phantom read 라고 한다.
        // 그래서 마지막으로 설정하는 최고수준의 격리단계가 바로 SERIALIZABLE 이다.

                        // SERIALIZABLE
        // commit 이 실행되지않은 transaction 이 존재하게되면 lock 을 통해서 waiting 을 하게된다.
        // 그래서 commit 이 실행되어야만 추가적인 로직 진행이 실행된다.
        // 둘다 none 으로 바뀌는 것이 이상하지않다. 그래서 데이터를 변경할라하면 다른쪽 transaction 이 끝날때까지 무조건 기다렸다가 처리하기때문에 데이터의 정확성은 100% 로 맞아떨어진다.
        // 하지만 그만큼 waiting 이 길어져서 성능에는좋지못한 격리수준이다.
        // read uncommitted 는 데이터 정확성이너무떨어지고 SERIALIZABLE 은 waiting 이 너무길어서 성능이 떨어진다. 따라서 이둘은 잘사용하지 않는다.
        // 특별한 상황에서만 처리하게되고
        // 일반적으로는 read committed 나 repeatable read 정도만 쓰게된다.
    }
}

// @Transactional 을 안하게되면 안했을때는 db 에 commit 이 바로바로 됬지만
// @Transactional 을 하게되면 그 함수가 다 끊나기 전까지 commit 이안되게 된다.

// RuntimeException 은 Transactional 내에서 발생하면 반영이 내용이 rollback 이되고
// 그냥 (CheckedException) 즉  Exception 은 Transactional 내에서 발생하더라도 반영 내용이 rollback 되지 않고 그냥 commit 되어 버린다.
// (CheckedException) 즉 Exception 경우에는 개발자가 해당 예외처리에대한 전체적인 책임을 다 가지게 된다.
// 이를 방지하기 위해서는 @Transactional Annotation 에 (rollbackFor = Exception.class) 를 써주면된다.
// 이렇게 하면 (Checked Exception) 에서도 rollback 을 해주므로 commit 이 안되게 해준다.

// 틈틈히 우리가 사용하는 open Source 들 특히 Spring 에 대한 구현들은 어떡게 동작하는지 한번씩 열어봐서 봐보는걸 추천한다.
// 그러면 더 깊고 더 최신의 정보들을 얻을수 있게된다.




// isolation : 격리라는 뜻이다.
// 동시에 발생하는 Transaction 간에 데이터 접근을 어떤 식으로 정리할것인지 그런단계가 isolation 격리 단계이다.


////////////////////////////////// Isolation.java ///////////////////////////////
//        READ_UNCOMMITTED(TransactionDefinition.ISOLATION_READ_UNCOMMITTED), level 0
        // COMMIT 되지 않은 데이터를 읽을수 있는 단계
        // 이런 case 를 dirty read 라고 한다 더러운것도 읽어본다
        // 아직은 깨긋하지않은 데이터지만 읽어본다
        // dirty read 의 문제점

//            READ_COMMITTED(TransactionDefinition.ISOLATION_READ_COMMITTED), level 1
//            REPEATABLE_READ(TransactionDefinition.ISOLATION_REPEATABLE_READ), level 2
//            SERIALIZABLE(TransactionDefinition.ISOLATION_SERIALIZABLE); level 3

//                              특징
//    아래쪽으로 갈수록 격리단계가 강력해지고 데이터의 정확성을 보장해주는 대신에
//    대신에 동시처리해주는 수행능력이 떨어지게 된다.
//    대신에 위쪽으로 갈수록 성능은 향상이 되는데 일부데이터의 정확성에 대해서는 보장해주지 못하는 경우가 간혹 발생하게된다.



///**
// * A constant indicating that dirty reads, non-repeatable reads and phantom reads
// * can occur. This level allows a row changed by one transaction to be read by
// * another transaction before any changes in that row have been committed
// * (a "dirty read"). If any of the changes are rolled back, the second
// * transaction will have retrieved an invalid row.
// * @see java.sql.Connection#TRANSACTION_READ_UNCOMMITTED
// */
//READ_UNCOMMITTED(TransactionDefinition.ISOLATION_READ_UNCOMMITTED),
//
//    /**
//     * A constant indicating that dirty reads are prevented; non-repeatable reads
//     * and phantom reads can occur. This level only prohibits a transaction
//     * from reading a row with uncommitted changes in it.
//     * @see java.sql.Connection#TRANSACTION_READ_COMMITTED
//     */
//    READ_COMMITTED(TransactionDefinition.ISOLATION_READ_COMMITTED),
//
//    /**
//     * A constant indicating that dirty reads and non-repeatable reads are
//     * prevented; phantom reads can occur. This level prohibits a transaction
//     * from reading a row with uncommitted changes in it, and it also prohibits
//     * the situation where one transaction reads a row, a second transaction
//     * alters the row, and the first transaction rereads the row, getting
//     * different values the second time (a "non-repeatable read").
//     * @see java.sql.Connection#TRANSACTION_REPEATABLE_READ
//     */
//    REPEATABLE_READ(TransactionDefinition.ISOLATION_REPEATABLE_READ),
//
//    /**
//     * A constant indicating that dirty reads, non-repeatable reads and phantom
//     * reads are prevented. This level includes the prohibitions in
//     * {@code ISOLATION_REPEATABLE_READ} and further prohibits the situation
//     * where one transaction reads all rows that satisfy a {@code WHERE}
//     * condition, a second transaction inserts a row that satisfies that
//     * {@code WHERE} condition, and the first transaction rereads for the
//     * same condition, retrieving the additional "phantom" row in the second read.
//     * @see java.sql.Connection#TRANSACTION_SERIALIZABLE
//     */
//    SERIALIZABLE(TransactionDefinition.ISOLATION_SERIALIZABLE);

                // propagation [ 영어 뜻 ] : 전달, 번식 이다
// 현재 있는 Transaction 과 또다른 클래스의 메서드의 트랜잭션간에 처리하는 것을
// 즉, Transaction 을 교통정리 해주는게 Transaction Jumper propagation 라하는 속성이다.
// 종류에는 REQUIRED , SUPPORTS , MANDATORY , REQUIRES_NEW , NOT_SUPPORTED , NEVER , NESTED 7 가지가 있다.

        // REQUIRED
// 일반적으로 많이쓰이는게 REQUIRED 이다. REQUIRED 는 다음과같다.
// 만약 기존의 사용하던 Transaction 이있다면 그 Transaction 을 사용하게되고
// Transaction 이 없다면 새로운 Transaction 을 생성해서 사용하겠다는 의미이다.
// 가장 직관적인 점퍼 속성이다. Transaction 이 필요한데 만약에 잇으면 재활용하고 없으면 직접 만들어서 사용하겠다라는 뜻이다.

//            BookServiceTest 를 보게되면 bookRepository.save() 는 Transaction 이 실행중이여도 반영이 되었는데
//            그이유는 아래와 같이 save 함수의 소스에 보면 @Transaction 어노테이션이 달려있었기 때문이였다.
//            즉, @Transaction default 값이 REQUIRED 이므로 없으면 직접 만들고 없으면 재활용 하기때문에
//            save 함수가 mysql db 에 반영이 된것이다.

//      CrudRepository.java >>> SimplejdbcRepository.java
//    @Transactional
//    @Override
//    public <S extends T> S save(S instance) {
//        return entityOperations.save(instance);
//    }


        // REQUIRES_NEW
// 있던 없던 상관없이 무조건 새로운 transaction 을 생성하는 방식이다.
// 새로운 transaction 을 만든다는것은 호출하는 쪽에 commit , rollback 과는 연관없이 자체적으로 commit 과 rollback 을 진행하겠다는 것이다.
// 따라서 위의 authorRepository.putAuthor() 에 Transaction propagation 을 REQUIRES_NEW 로 해주게되면
// 우리가 예상했던것처럼 bookRepository 는 commit 이되고 authorRepository 는 예외가 발생해서 rollback 이 되게된다.
                            // 두개가 완전히 독립된 transaction 으로 동작된다.

        // NESTED
// 별도의 Transaction 을 생성하는 것이 아니라 호출하는 쪽 의 transaction 속성을 그대로 따라하게된다.
// 생성을 하는것이 아닌데 조금은 개별적으로 움직일수있는 하나의 transaction 이지만 약간은 분리되어 동작할수 시킬수 있는 옵션이라고 보면된다.
// 만약에 author 서비스 자체가 실패를 하게되면 author 는 rollback 이 일어나는데 이미 기존에 실행됫던 save 의 대해서는 commit 이 일어나게된다.
                            // 독립되었기 보다는 종속적이지만 종속적인 transaction 은 상위에 영향을 주지않는다.
                            // NESTED 잘 거의 안쓴다. 생각한대로 동작하지 않아서


        // SUPPORTS
// 호출하는 쪽에서 Transaction 이 있다면 해당 transaction 을 사용하게된다. 즉 재활용한다. [ 이것만 보면 REQUIRED 랑 비슷하다 ]
// 하지만 Transaction 이 없다면 REQUIRED 는 새로운 Transaction 을 생성했는데 SUPPORTS 는 굳이 새로 생성하지 않는다.
// 그래서 Transaction 을 생성하지않고 Transaction 없이 처리를 하게된다. ( Transaction 이 있으면 지원해주겠다 그래서 이름이 SUPPORTS 이다. )


        // NOT_SUPPORTED
// 호출하는 쪽에서 Transaction 을 가지고 있으면 잠시 멈춘다. Transaction 하고 별개로
// REQUIRES_NEW 랑 비슷하긴 한데 REQUIRES_NEW 는 별개의 Transaction 을 생성하는데
// 여기서는 해당영역은 Transaction 없이 동작을 하도록 별개로 설정하게된다. 그리고 호출하는 쪽 그 Transaction 은 호출 받는 Transaction 실행이 사실 Transaction 이 없는 상태니까
// 실행이 완료된 이후에 별도로 처리를 하게된다. 그래서 두개는 서로 별개로 처리가 되도록 되어있다.


        // MANDATORY
// 필수적으로 Transaction 이 존재 해야한다. REQUIRED 의 경우에는 실행하는 쪽에서 생성된 Transaction 이 없다면 새로만들어서 처리했다.
// 하지만 MANDATORY 의 경우에는 이미 생성된 Transaction 이 있어야 하고 없으면 오류를 발생시킨다.


        // NEVER
// Transaction 이 없어야 한다. 하지만 이미 Transaction 이 존재한다면 오류를 낸다.


// 진짜 아주아주 특별한 경우가 아니라면 거의 대부분 REQUIRED 나 REQUIRES_NEW 를 쓴다.
