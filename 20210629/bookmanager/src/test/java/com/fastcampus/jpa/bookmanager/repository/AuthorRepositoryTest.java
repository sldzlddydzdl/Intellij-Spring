package com.fastcampus.jpa.bookmanager.repository;

import com.fastcampus.jpa.bookmanager.domain.Author;
import com.fastcampus.jpa.bookmanager.domain.Book;
import com.fastcampus.jpa.bookmanager.domain.BookAndAuthor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;

    @Test
    @Transactional // LazyInitializationException 을 해결하기위해 붙이는 annotation
    void manyToManyTest(){
        Book book1 = givenBook("책1");
        Book book2 = givenBook("책2");
        Book book3 = givenBook("개발책1");
        Book book4 = givenBook("개발책2");

        Author author1 = givenAuthor("martin");
        Author author2 = givenAuthor("steve");

        BookAndAuthor bookAndAuthor1 = givenBookAndAuthor(book1, author1);
        BookAndAuthor bookAndAuthor2 = givenBookAndAuthor(book2, author2);
        BookAndAuthor bookAndAuthor3 = givenBookAndAuthor(book3, author1);
        BookAndAuthor bookAndAuthor4 = givenBookAndAuthor(book3, author2);
        BookAndAuthor bookAndAuthor5 = givenBookAndAuthor(book4, author1);
        BookAndAuthor bookAndAuthor6 = givenBookAndAuthor(book4, author2);


//        book1.setAuthors(Lists.newArrayList(author1));
//        book2.setAuthors(Lists.newArrayList(author2));
//        book3.setAuthors(Lists.newArrayList(author1, author2));
//        book4.setAuthors(Lists.newArrayList(author1, author2));

//        book1.addAuthor(author1);
//        book2.addAuthor(author2);
//        book3.addAuthor(author1, author2);
//        book4.addAuthor(author1, author2);
//
////        author1.setBooks(Lists.newArrayList(book1, book3 ,book4));
////        author2.setBooks(Lists.newArrayList(book2, book3 ,book4));
//
//        author1.addBook(book1, book3, book4);
//        author2.addBook(book2, book3, book4);

        book1.addBookAndAuthors(bookAndAuthor1);
        book2.addBookAndAuthors(bookAndAuthor2);
        book3.addBookAndAuthors(bookAndAuthor3, bookAndAuthor4);
        book4.addBookAndAuthors(bookAndAuthor5, bookAndAuthor6);

        author1.addBookAndAuthors(bookAndAuthor1, bookAndAuthor3, bookAndAuthor5);
        author2.addBookAndAuthors(bookAndAuthor2, bookAndAuthor4, bookAndAuthor6);


        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3 , book4));
        authorRepository.saveAll((Lists.newArrayList(author1, author2)));

//        System.out.println("authors through book : " + bookRepository.findAll().get(2).getBookAndAuthors());
//        System.out.println("books through author " +authorRepository.findAll().get(0).getBooks());

        bookRepository.findAll().get(2).getBookAndAuthors().forEach(o ->System.out.println(o.getAuthor()));
        authorRepository.findAll().get(0).getBookAndAuthors().forEach(o ->System.out.println(o.getBook()));

        // 보통은 ManyToMany 를 많이 피할려고 처음부터 설계에서 매우 심려를 기울인다.
        // 보통은 oneToMany, ManyToOne, OneToOne 에서 끝낼려고한다.
        // 보통은 상점에서 주문 table 에서 존재한다.

        // 주문 같은 경우는

        // User

        // User/Product 이구조에서는 User 와 Product 가 ManyToMany 구조 일수 밖에없다.
        // 위영역이 user_products 라는 중간 table 이 생긴다.
        // user_products -> order 라는 별도의 domain 을 생성을하고 oneToMany 와 ManyToOne 을 조합해서 생성한다.

        // Product

    }

    private Book givenBook(String name){
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name){
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

    private BookAndAuthor givenBookAndAuthor(Book book, Author author){
        BookAndAuthor bookAndAuthor = new BookAndAuthor();
        bookAndAuthor.setBook(book);
        bookAndAuthor.setAuthor(author);

        return bookAndAuthorRepository.save(bookAndAuthor);

    }

}