package com.fastcampus.jpa.bookmanager.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//public interface BookNameAndCategory {
//    String getName();
//    String getCategory();
//}
// 위처럼 interface 로 하는 방법도 있다.

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookNameAndCategory {
        private String name;
        private String category;
}
