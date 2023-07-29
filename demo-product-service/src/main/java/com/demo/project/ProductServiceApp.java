package com.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Hello world!
 *
 */
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class ProductServiceApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(ProductServiceApp.class,args);
        System.out.println( "Hello World!" );
    }
}
