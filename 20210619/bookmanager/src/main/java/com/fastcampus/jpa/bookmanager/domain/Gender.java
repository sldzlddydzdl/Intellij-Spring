package com.fastcampus.jpa.bookmanager.domain;

public enum Gender {
    MALE,
    FEMALE

//    MALE,
//    FEMALE
//    일 때는 0의 인덱스값을 가지고 있기 때문에 0이출력된다.

//    Hibernate:
//    select
//    user0_.id as id1_1_,
//    user0_.created_at as created_2_1_,
//    user0_.email as email3_1_,
//    user0_.gender as gender4_1_,
//    user0_.name as name5_1_,
//    user0_.updated_at as updated_6_1_
//            from
//    user user0_
//    User(id=1, name=martin, email=martin@fastcampus.com, gender=MALE, createdAt=2021-06-18T01:44:01.576944, updatedAt=2021-06-18T01:44:01.576944, testDate=null)
//    User(id=2, name=dennis, email=dennis@fastcampus.com, gender=null, createdAt=2021-06-18T01:44:01.584923, updatedAt=2021-06-18T01:44:01.584923, testDate=null)
//    User(id=3, name=sophia, email=sophia@slowcampus.com, gender=null, createdAt=2021-06-18T01:44:01.584923, updatedAt=2021-06-18T01:44:01.584923, testDate=null)
//    User(id=4, name=james, email=james@slowcampus.com, gender=null, createdAt=2021-06-18T01:44:01.585920, updatedAt=2021-06-18T01:44:01.585920, testDate=null)
//    User(id=5, name=martin, email=martin@another.com, gender=null, createdAt=2021-06-18T01:44:01.585920, updatedAt=2021-06-18T01:44:01.585920, testDate=null)
//    Hibernate:
//    select
//        *
//    from
//    user limit 1;
//
//    출력 : 0


//    그러나
//    FEMALE,
//    MALE
//            일때는 1의 인덱스값을 가지고 있기 때문에 1이 출력된다.

//    Hibernate:
//    select
//    user0_.id as id1_1_,
//    user0_.created_at as created_2_1_,
//    user0_.email as email3_1_,
//    user0_.gender as gender4_1_,
//    user0_.name as name5_1_,
//    user0_.updated_at as updated_6_1_
//            from
//    user user0_
//    User(id=1, name=martin, email=martin@fastcampus.com, gender=MALE, createdAt=2021-06-18T01:53:22.148704, updatedAt=2021-06-18T01:53:22.148704, testDate=null)
//    User(id=2, name=dennis, email=dennis@fastcampus.com, gender=null, createdAt=2021-06-18T01:53:22.157676, updatedAt=2021-06-18T01:53:22.157676, testDate=null)
//    User(id=3, name=sophia, email=sophia@slowcampus.com, gender=null, createdAt=2021-06-18T01:53:22.157676, updatedAt=2021-06-18T01:53:22.157676, testDate=null)
//    User(id=4, name=james, email=james@slowcampus.com, gender=null, createdAt=2021-06-18T01:53:22.157676, updatedAt=2021-06-18T01:53:22.157676, testDate=null)
//    User(id=5, name=martin, email=martin@another.com, gender=null, createdAt=2021-06-18T01:53:22.158673, updatedAt=2021-06-18T01:53:22.158673, testDate=null)
//    Hibernate:
//    select
//        *
//    from
//    user limit 1;
//
//    출력 : 1


    }
