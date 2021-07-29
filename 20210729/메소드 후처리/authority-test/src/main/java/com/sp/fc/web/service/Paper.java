package com.sp.fc.web.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paper {

      private Long paperId;
      private String title;
      private String tutorId;
      private List<String> studentIds;
      private State state;

      public static enum State{
          PREPARE , // 제출 중
          READY , // 시험 시작
          END  // 시험 종료
      }

}
