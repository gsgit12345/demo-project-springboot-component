package com.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableEurekaClient
public class ProductServiceApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductServiceApp.class,args);
        System.out.println( "Hello World!" );
    }
}
