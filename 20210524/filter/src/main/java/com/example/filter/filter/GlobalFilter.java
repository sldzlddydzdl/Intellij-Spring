package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
//@Component
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 전처리 구간
//        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        // 이렇게 하면 BufferedReader 할 때 이미한번 읽어버려서 다음에 읽을것이 없어서 에러가나므로
        // Caching 을 이용하여 읽어야 한다.

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest)request);
        // ByteArrayOutputStream 에다가 미리 내용을 담아둔다
        // 누가 다음에 읽을라 할때 ByteArrayOutputStream 에 저장해둔 것을 가져와서 읽는다.
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

//        String url = httpServletRequest.getRequestURI();
//        BufferedReader br = httpServletRequest.getReader();
//
//        br.lines().forEach(line -> {
//            log.info("url : {} , line :  {}" ,url , line);
//        });

        chain.doFilter(httpServletRequest, httpServletResponse);

        // 후처리 구간

        // req
        String url = httpServletRequest.getRequestURI();

        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        log.info("request url : {} , request body : {} " , url , reqContent );


        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();
    // client body 에 아무겄도 출력이 안된다. 이미 한번 다 읽었기 때문에
//        따라서 한번 더 호출 해주는 메소드를 써야한다.

        httpServletResponse.copyBodyToResponse();


        log.info("response status : {} , responseBody : {} " , httpStatus, resContent   );

    }
}
