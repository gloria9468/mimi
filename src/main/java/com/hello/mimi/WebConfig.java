package com.hello.mimi;

import com.hello.mimi.standard.post.model.PhotoPostDTO;
import com.hello.mimi.standard.post.model.PostDTO;
import com.hello.mimi.vo.FilePathMaker;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration
public class WebConfig {
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);  // 기본 값을 Locale.KOREA로 설정
        return localeResolver;
    }

//    @Bean
//    public FilePathMaker filePathMaker() {
//        FilePathMaker filePathMaker = new FilePathMaker();
//        System.out.println("bean filepathMaker address ===="+filePathMaker);
//        return filePathMaker;
//    }
}