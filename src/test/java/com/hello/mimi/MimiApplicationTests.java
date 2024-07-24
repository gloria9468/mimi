package com.hello.mimi;

import com.hello.mimi.util.bean.SpringApplicationContext;
import com.hello.mimi.util.config.WebConfig;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class MimiApplicationTests {
    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

    @Test
    void contextLoads() {
    }

    @Test
    void getAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(WebConfig.class);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("======"+beanDefinitionName);
        }
    }

    @Test
    void testMapperScan() throws IOException {
        //System.out.println("springProfilesActive-- "+ springProfilesActive);
        String basePackage = "com.hello.mimi.mapper." + springProfilesActive;
        List<String> subPackages = getSubPackages(basePackage);

        //System.out.println(subPackages.size());
        //subPackages.forEach(System.out::println);

        Set<String> packages = new HashSet<>();
        subPackages.forEach(packages::add);

        // @Mapper 어노테이션이 붙은 모든 빈 이름을 가져옵니다.
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(Mapper.class);

        for (String beanName : beanNames) {
            String firstStr = beanName.substring(0,1);
            String findBeanClassName = beanName.replaceFirst(firstStr, firstStr.toUpperCase());
            //System.out.println("firstStr = " + firstStr + "-- findBeanClassName = " + findBeanClassName);

            // 패키지 내에서 클래스를 검색합니다.
            String packageName = findPackageNameForClass(packages, findBeanClassName);

            // 패키지 이름을 출력합니다.
            if (packageName != null) {
                System.out.println("Package name: " + packageName);
            } else {
                System.out.println("Class not found in the provided packages.");
            }
            Assertions.assertTrue(packageName.contains(springProfilesActive));
        }



    }

    private static String findPackageNameForClass(Set<String> packages, String className) {
        // 패키지 리스트에서 클래스를 검색합니다.
        return packages.stream()
                .filter(pkg -> classExists(pkg + "." + className))
                .findFirst()
                .orElse(null);
    }

    private static boolean classExists(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static List<String> getSubPackages(String basePackage) throws IOException {
        List<String> packages = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(basePackage.replace('.', '/'));

        if (resource != null) {
            String path = URLDecoder.decode(resource.getFile(), "UTF-8");
            File directory = new File(path);
            if (directory.exists() && directory.isDirectory()) {
                for (File file : directory.listFiles()) {
                    if (file.isDirectory()) {
                        String packageName = basePackage + "." + file.getName();
                        packages.add(packageName);
                        packages.addAll(getSubPackages(packageName));
                    }
                }
            }
        }
        return packages;
    }
}
