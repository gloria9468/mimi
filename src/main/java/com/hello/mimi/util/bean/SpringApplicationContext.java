package com.hello.mimi.util.bean;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContext implements ApplicationContextAware {

    private static ApplicationContext CONTEXT = null;

    public static ApplicationContext getApplicationContext() {
        return CONTEXT;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }

}
