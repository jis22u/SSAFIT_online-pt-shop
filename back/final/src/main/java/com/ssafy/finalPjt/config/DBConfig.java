package com.ssafy.finalPjt.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.finalPjt.model.dao")
public class DBConfig {

}
