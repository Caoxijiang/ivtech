package com.ivtech.qaii;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@MapperScan("com.ivtech.qaii.mapper")

@ServletComponentScan
//@ComponentScan(basePackages = {"com.ivtech.qaii.mapper"})
public class IvtechApplication{

    public static void main(String[] args) {
        SpringApplication.run(IvtechApplication.class, args);
    }

//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
//        System.out.println("外部tomcat,chapter启动!");
//        return application.sources(IvtechApplication.class);
//    }


}
