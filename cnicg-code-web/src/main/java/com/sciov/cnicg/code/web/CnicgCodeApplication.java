package com.sciov.cnicg.code.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Created by Tinker on 2017/9/20.
 */
@EnableAutoConfiguration
//@EnableDiscoveryClient
@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackages = {"com.sciov","cn.cnicg"})
@MapperScan("com.sciov.cnicg.code.persist.mapper")
public class CnicgCodeApplication {

    public static void main(String[] args) {   
    	SpringApplication.run(CnicgCodeApplication.class, args);
    }
}
