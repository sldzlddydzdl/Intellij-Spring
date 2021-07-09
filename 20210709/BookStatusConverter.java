package com.fastcampus.jpa.bookmanager.domain.converter;

import com.fastcampus.jpa.bookmanager.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;



@Converter(autoApply = true)
// convert 를 자주 사용하게 될때 @autoApply 옵션을 사용해라
// autoApply 는 우리가 생성햇던 컨버터 클래스에 달려있는 컨버터 어노테이션의 autoApply 를 지원해준다.
// 해당 객체의 필드에 @Convert(convert = BookStatusConvert.class) 가 없어도
// 해당 객체타입 BookStatus 이 entity 의 필드로 선언되있으면 그 convert 를 통해서 변환이 되도록 처리해준다 그래서 이름이 autoApply 이다.
// autoApply 가 편하긴하지만 하나 주의해야할 사항이있다. 일반적으로는 이렇게 BookStatus 처럼 개발자가 직접 생성한 클래스 타입에 한해서 활용해야한다.
// 만약에 우리가 BookStatus 가 아니라 StringConverter , IntegerConverter 를 만들어서 autoapply 를 적용하게되면
// 모든 varchar 타입 모든 number 타입 의 컬럼들은 해당 컨버트를 타게 될것이기 때문에 예상치 못한 얼래는 그 컨버터를 타면안되는 그런 컬럼들 마저도 타게될수 있어서
// 문제점이 발생할수 있다. 
public class BookStatusConverter implements AttributeConverter<BookStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
//        return attribute.getCode();
        return null;
    }


    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
