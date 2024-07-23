package com.hello.mimi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@SpringBootTest
class MimiApplicationTests {

    ApplicationContext ac = new AnnotationConfigApplicationContext(WebConfig.class);

    @Test
    void contextLoads() {
    }

    @Test
    void getAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("======"+beanDefinitionName);
        }
    }
}
