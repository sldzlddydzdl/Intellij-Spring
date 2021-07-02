package com.fastcampus.jpa.bookmanager.service;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.repository.AuthorRepository;
import com.fastcampus.jpa.bookmanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

//    @Autowired
    private final BookRepository bookRepository;

//    @Autowired
    private final AuthorRepository authorRepository;


//    public void put(){
//        this.putBookAndAuthor();
//    }

    // 이미 put 에서 bean 으로 등록되서 들어간다음 그안에있는 Transactional 메서드를 다시들어가게되면
    // 여기에서는 putBookAndAuthor 의 @Transactional 어노테이션이 무효화가 되므로
    // RuntimeException 임에도 불구하고 rollback 이 안되고 commit 되게 된다.
    // public 이거나 private 이거나 없어도 해당된다.

    @Transactional/*(rollbackFor = Exception.class)*/ // import 가 두개가있는데 두개 아무거나 써도 Transactional 기능은 다 구현해준다.
    /*public*/public void putBookAndAuthor() /*throws Exception */{
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

//        throw new Exception("오류가 나서 DB commit 이 발생하지 않습니다");
        throw new RuntimeException("오류가 나서 DB commit 이 발생하지 않습니다");

        // Transactional 에서 exception 이 발생하면 함수가 끝나도 db에 반영되지 않게된다.
        // commit 이 되지않고 rollback 이 되게된다.
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void get(Long id){
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());
        // transaction 걸려서 mysql 에서 category 를 none 으로 update 해도 반영되지않아서
        //  출력하게되면 그대로 category 는 NULL 을 유지한다.

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());
        // 첫번째 와 마찬가지로
        // transaction 걸려서 mysql 에서 category 를 none 으로 update 해도 반영되지않아서
        //  출력하게되면 그대로 category 는 NULL 을 유지한다.
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
