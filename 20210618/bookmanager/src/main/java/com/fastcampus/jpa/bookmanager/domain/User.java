package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table (name = "user" , indexes = {@Index(columnList =  "name")} , uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}) // 클래스 이름을 본따서 알아서 TABLE 이름을 만들어준다.

        // 밑에처럼
//    create table user (
//            id bigint not null,
//            created_at timestamp,
//            email varchar(255),
//            name varchar(255),
//            updated_at timestamp,
//            primary key (id)
//            )

    // (name = "user_legacy" ) 라 해주면 테이블 이름을 user_legacy 로 해준다 사용자가 설정할수도있다.
// 하지만 클래스 이름과 같은게 좋다.
//    create table user_legacy (
//            id bigint not null,
//            created_at timestamp,
//            email varchar(255),
//            name varchar(255),
//            updated_at timestamp,
//            primary key (id)
//            )

//create index IDXgj2fy3dcix7ph7k8684gka40c on user (name) -> indexes = { @Index(columnList = "name") }
//add constraint UKob8kqyqqgmefl0aco34akdtpe unique (email) -> uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
// email 과 name 두개를 엮어서 unique 한 constraint 를 만들겠다.

//@EntityListeners(value = {MyEntityListener.class , UserEntityListener.class})
@EntityListeners(value = {/*AuditingEntityListener.class,*/ UserEntityListener.class})
public class User extends BaseEntity implements Auditable {
    @Id // primary key
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
//    @Column(unique = true) // 이 컬럼은 unique 한 속성을 가지게된다
    private String email;

    @Enumerated(value = EnumType.STRING) // Enum 은 ORDINAL 과 STRING 이 있는데 ORDINAL 을 하게되면 잠재적 버그를 일으킬수 있으므로
                                            //
//    public enum EnumType {
//        /** Persist enumerated type property or field as an integer. */
//        ORDINAL,
//
//        /** Persist enumerated type property or field as a string. */
//        STRING
//    }
    private Gender gender;


//    @Column(name = "crtdat")
//    private LocalDateTime crtdat; // ORM 에서 컬럼하고 이속성을 MAPPING 을 해줄것이다.
    // 그러면 Test 할 때
//    new User.getCrtdat(); 이런식으로 가독성이없는 코드를 짤수밖에 없어진다.
//    private LocalDateTime createdAt;
//            그래서 변수 이름은 createdAt 으로 가독성을 높이고 Column name 만 crtdat 으로한다.
//                create table user (
//                    id bigint not null,
//                    crtdat timestamp,
//                    email varchar(255),
//                    name varchar(255),
//                    updated_at timestamp,
//                    primary key (id)
//                 )
    //              위처럼 column name 이 crtdat 으로 바꼇다!

//    @Column(nullable = false)
//    @Column(updatable = false)
//    @CreatedDate // 자동으로 해당값을 처리해줌
//    private LocalDateTime createdAt;
//
////    create table user (
////        id bigint not null,
////        created_at timestamp not null,
////        email varchar(255),
////        name varchar(255),
////        updated_at timestamp,
////        primary key (id)
////    )
//            // 위처럼 created_at 의 속성이 not null 이된다.
//            // null 을 허용하는지 안하는지는 매우 중요한 결정사항이다.
//
////    @Column(insertable = false)
//    @LastModifiedDate // 자동으로 해당값을 처리해줌
//    private LocalDateTime updatedAt;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

//
//    Column(updatable = false)
//    private LocalDateTime createdAt;
//
//    @Column(insertable = false)
//    private LocalDateTime updatedAt;
                    // 위에속성은 createdAt 생성할때만 값ㅇ ㅣ바껴야 하므로 updateTable = false 로해줘서 insert 일때만 column 에 추가해주고 나머지는 추가를 안해줘서 update 가 안되게 한다.
        //    Hibernate:
        //    call next value for hibernate_sequence
        //    Hibernate:
        //    insert
        //            into
        //    user
        //            (created_at, email, name, id)
        //    values
        //            (?, ?, ?, ?)
        //    Hibernate:
        //    select
        //    user0_.id as id1_1_0_,
        //    user0_.created_at as created_2_1_0_,
        //    user0_.email as email3_1_0_,
        //    user0_.name as name4_1_0_,
        //    user0_.updated_at as updated_5_1_0_
        //            from
        //    user user0_
        //    where
        //    user0_.id=?
        //    Hibernate:
        //    select
        //    user0_.id as id1_1_0_,
        //    user0_.created_at as created_2_1_0_,
        //    user0_.email as email3_1_0_,
        //    user0_.name as name4_1_0_,
        //    user0_.updated_at as updated_5_1_0_
        //            from
        //    user user0_
        //    where
        //    user0_.id=?
        //    Hibernate:
        //    update
        //            user
        //    set
        //    email=?,
        //    name=?,
        //    updated_at=?
        //    where
        //    id=?

//    @Transient // db 데이터에 반영이안되고 생명객체와 생명주기가 안생기게된다.
//    private String testDate;
    // 이 testdate 는 db에 반영하고싶지않을대

//    @PrePersist // insert method 가 호출되기 전에 실행되는 메소드
//    @PreUpdate // merge method 가 호출되기 전에 실행되는 메소드
//    @PreRemove // delete method 가 호출되기 전에 실행되는 메소드
//    @PostPersist // insert method 가 호출된 이후에 실행되는 메소드
//    @PostUpdate // merge method 가 호출된 이후에 실행되는 메소드이다
//    @PostRemove // delete method 가 호출된 후에 실행되는 메소드
//    @PostLoad // select 조회가 일어난 직후에 일어나는 method 이다.

//    @PrePersist
//    public void prePersist(){
////        System.out.println(">>> prePersist");
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }

    // 위와같이함으로써 개발자가 실수를 데이터를 넣지않았을때의 실수를 방지할수 있다.

//    @PostPersist
//    public void postPersist(){
//        System.out.println(">>> PostPersist");
//    }
//
//    @PreUpdate
//    public void preUpdate(){
////        System.out.println(">>> preUpdate");
//        this.updatedAt = LocalDateTime.now();
//    }

//    @PostUpdate
//    public void postUpdate(){
//        System.out.println(">>> PostUpdate");
//    }
//
//    @PreRemove
//    public void preRemove(){
//        System.out.println(">>> PreRemove");
//    }
//
//    @PostRemove
//    public void postRemove(){
//        System.out.println(">>> PostRemove");
//    }
//
//    @PostLoad
//    public void postLoad(){
//        System.out.println(">>> PostLoad");
//    }
//
//


}
