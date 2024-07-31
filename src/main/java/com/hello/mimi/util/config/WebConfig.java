package com.hello.mimi.util.config;

import com.hello.mimi.util.vo.FilePathMaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.CacheControl;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


@Configuration
// @PropertySource("classpath:application.properties") // 없어도 됨.
public class WebConfig implements WebMvcConfigurer {
    @Value("${file-store-path}")
    private String fStorePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file-store-path/**")
                .addResourceLocations(fStorePath)
                .setCacheControl(CacheControl.noCache().cachePrivate());

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/public/")
                .setCachePeriod(3600); // 3600 :: 1시간
    }


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


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}