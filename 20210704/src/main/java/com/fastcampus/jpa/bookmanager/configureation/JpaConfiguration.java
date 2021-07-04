package com.fastcampus.jpa.bookmanager.configureation;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // 별도의 Bean 에서 실행시켜주는 방법
public class JpaConfiguration {
}
