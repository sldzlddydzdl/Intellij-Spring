package com.fastcampus.jpa.bookmanager.domain;

import java.time.LocalDateTime;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@AllArgsConstructor // 객체의 모든 필드를 받아서 생성하는 생성자
@NoArgsConstructor // 인자가 없는 생성자
@RequiredArgsConstructor // 꼭 필요한 인자만을 생성해서 생성해주는 생성자
// 필드에 @NonNull 이 있거나, @Nullable 같은 Annotation 애들이 있으면
// 개내를 의식해서 생성자를 만들어준다.
@EqualsAndHashCode
@Data // @code @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode
//@Builder //
//    Test
//        User user3 = User.bulder()
//            .name("martin")
//            .email("martin@fastcampus.com")
//            .build()
@Entity // Primary Key 가 꼭 필요하다!!!
public class User {

//    ctrl + shift + t   -> Test 코드 생성

    @Id
    @GeneratedValue // 순차적으로 증가한다는 뜻
    private Long id;


//    @Getter
//    @Setter
    @NonNull
    private String name;
//    @Getter
//    @Setter
    @NonNull
    private String email;
//    @Getter
//    @Setter
    private LocalDateTime createdAt;
//    @Getter
//    @Setter
    private LocalDateTime updateAt;

//    public String getName(){
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public LocalDateTime getUpdateAt() {
//        return updateAt;
//    }
//
//    public void setUpdateAt(LocalDateTime updateAt) {
//        this.updateAt = updateAt;
//    }

//    @Override
//    public String toString() {
//
//        return getClass().getName() +
//                " : " +
//                "name='" + name + '\'' +
//                ", email='" + email + '\'' +
//                ", createdAt=" + createdAt +
//                ", updateAt=" + updateAt;
//    }

}

