package com.hello.mimi.util;

import com.hello.mimi.WebConfig;
import com.hello.mimi.vo.FilePathMaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanManager {
    private static AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(FilePathMaker.class);

    public static Object getBean(String beanName) {
        return ac.getBean(beanName);
    }
}
