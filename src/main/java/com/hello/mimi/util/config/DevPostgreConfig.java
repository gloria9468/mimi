package com.hello.mimi.util.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev-postgre")
@MapperScan("com.hello.mimi.mapper.dev.postgre")
public class DevPostgreConfig {

}
