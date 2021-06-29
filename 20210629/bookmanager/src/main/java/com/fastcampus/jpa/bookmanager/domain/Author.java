package com.fastcampus.jpa.bookmanager.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Author extends  BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    //    @ManyToMany
    @OneToMany
    @JoinColumn(name = "author_id")
    @ToString.Exclude // console 창  stackOverflow 예방
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

//    public void addBook(Book... book){
//        Collections.addAll(this.books, book);
////        if (book != null) {
////            for(Book book1 : book){
////                this.books.add(book1);
////            }
////        }
//    }

    public void addBookAndAuthors(BookAndAuthor... bookAndAuthors) {
        Collections.addAll(this.bookAndAuthors, bookAndAuthors);
    }
}