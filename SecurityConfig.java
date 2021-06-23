package com.sp.fc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter;
import org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter;


//@Order(1) // filter 순서를 정해주는 annotation 이다.
@EnableWebSecurity(debug = true) // 어떤 Security filter 들이 동작했는지 console 에 찍어준다.
@EnableGlobalMethodSecurity(prePostEnabled = true) // 지금부터 내가 prePost 로 권한 체크를 하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    DefaultLoginPageGeneratingFilter loginPageFilter;
    DefaultLogoutPageGeneratingFilter logoutPageFilter;

    @Override // application.yml 에는 user id , pw 를 한쌍밖에 못만들어서
                // WebSecurityConfigurerAdapter 를 상속받은아이에서 생성 아이들을 만들어줄수있다.
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(User.builder()
                .username("user2")
                .password(passwordEncoder().encode("2222"))
                .roles("USER"))
                .withUser(User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("3333"))
                        .roles("ADMIN"))
                ; // 위대로만 하면 에러나는데 이유는 PASSWORD 를 인코딩 하지않아서 오류가난다.
                    // 꼭 Password 에서는 인코딩을 해줘야한다.

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    // Spring Security 는 기본적으로 모든페이지를 다막고시작한다.
    // 홈페이지같은 경우에는 누구나 접근할수 있게 풀어주고싶은데
    // 이를 풀어주기 위해서는 antMatchers("/").permitAll().anyRequest().authenticated() 를 해준다.
    // 기본 상속받는것은 아래와 같은데 아래를 바꿔준다.
    // http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
    //		http.formLogin();
    //		http.httpBasic();
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/api/**"); // 모든 request 에 대해서 check 를 하고싶다.
//        http.authorizeRequests((requests) ->
//                requests.antMatchers("/"    ).permitAll().anyRequest()
//                        .authenticated());
//        http.formLogin();
//        http.httpBasic();

//    위를 주석처리하면 authorizeRequests , formLogin, httpBasic 에서 작동햇던 필터들이 작동을안하게된다.



        http.headers().disable()
                .csrf().disable()
                .formLogin(login->
                                login.defaultSuccessUrl("/", false) // 내가 로그인에 성공 하면 root 페이지로 가라
                                                                                            // false 를 안걸어주면 성공안해도 root 로가게되서 불편해진다.
                        )
                .logout().disable()
                .requestCache().disable()
                ;

//        HeaderWriterFilter
//        CsrfFilter
//        LogoutFilter
//        RequestCacheAwareFilter 위처럼 disable 해주면 4가지 필터들이 이에맞게 동작을 안하게된다.
    }


}
