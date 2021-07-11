package com.fastcampus.jpa.bookmanager.domain;

import com.fastcampus.jpa.bookmanager.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = AuditingEntityListener.class)
public class UserHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "user_id" , insertable = false, updatable = false)
//    private Long userId;

    private String name;

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


//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;

    @ManyToOne
    @ToString.Exclude
    private User user;

}
