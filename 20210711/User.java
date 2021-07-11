package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import com.fastcampus.jpa.bookmanager.domain.listener.UserEntityListener;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class User extends BaseEntity  {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ToString.Exclude
        // 아래와 같이 user 와 user_histories 사이에 이상한 중간 맵핑 table 이 생성된다.
        // 이를 방지 하기 위해 @JoinColumn 이라는 annotation 을 달아준다.
        // @JoinColumn Entity 가 어떤 컬럼으로 조인할지 지정해준느 annotation 이다.
//    create table user_user_histories (
//            user_id bigint not null,
//            user_histories_id bigint not null
//    )

    // user Entity 에서는 userHistory 를 insertable 도 false 로해주고 updatable 도 false 로 해줘서
    // 삽입과 수정을불가능하게 해준다. insertable, updatable 은 default 값으로 true 로 되어있어서
    // JoinColumn 에서 사용자가 설정해줘야한다.

    private List<UserHistory> userHistories = new ArrayList<>();
    // get userHistories 를 했을때 Null pointException 을 방지하기위해 new ArrayList 처럼 기본값을 주어준다.

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

//    @Embedded
//    private Address address;

//    private String homeCity;
//    private String homeDistrict;
//    private String homeDetail;
//    private String homeZipCode;
//
//    private String companyCity;
//    private String companyDistrict;
//    private String companyDetail;
//    private String companyZipCode;

    // 위처럼 쓰지말자

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "district", column = @Column(name = "home_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "home_address_detail")), // 기존에 Address 에 detail 은 @Column(name = "address_detail") 이 되있는데
                                                                                                 // @AttributeOverride 는 말그대로 Override 해줌으로 attributeoverride 에서
                                                                                                 // 설정한 Column name 이 들어가게 된다.
            @AttributeOverride(name = "zipCode" , column = @Column(name = "home_zip_code"))
    })
    // 일반적으로 디비 테이블에서는 중복이름을 갖는 column 이름을 허용하지 않는다.
    // 그래서 중복된 column 값을 갖기위해서 해줄수 있는것이
    // @AttributeOverrides 이다.
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "company_city")),
            @AttributeOverride(name = "district", column = @Column(name = "company_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "company_address_detail")), // 기존에 Address 에 detail 은 @Column(name = "address_detail") 이 되있는데
            // @AttributeOverride 는 말그대로 Override 해줌으로 attributeoverride 에서
            // 설정한 Column name 이 들어가게 된다.
            @AttributeOverride(name = "zipCode" , column = @Column(name = "company_zip_code"))
    })
    private Address companyAddress;

}
