package com.hello.mimi.util.bean;

import org.springframework.context.ApplicationContext;

public class BeanManager {
/* 이렇게 하면 올라가 있는 bean 을 가져오는 게 아니게 되더라고??  주소값이 달랐었음.
    private static AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(WebConfig.class);
    public static Object getBean(String beanName) {
        return ac.getBean(beanName);
    }
*/

    public static Object getBean(String beanName) {
        ApplicationContext applicationContext = SpringApplicationContext.getApplicationContext();
        if (applicationContext == null) {
//            System.out.println("Spring의 ApplicationContext초기화 안됨");
        }
        assert applicationContext != null;
        return applicationContext.getBean(beanName);
    }

}
