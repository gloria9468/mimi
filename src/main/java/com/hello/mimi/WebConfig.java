package com.hello.mimi;

import com.hello.mimi.util.vo.FilePathMaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration
// @PropertySource("classpath:application.properties") // 없어도 됨.
public class WebConfig {
    @Value("${file-store-path}")
    private String fStorePath;

    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA);  // 기본 값을 Locale.KOREA로 설정
        return localeResolver;
    }

    @Bean
    public FilePathMaker filePathMaker() {
        FilePathMaker filePathMaker = new FilePathMaker(fStorePath);
        System.out.println("bean filepathMaker address ===="+filePathMaker);
        return filePathMaker;
    }
}