package com.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient

public class OrderServiceDemo
{
    public static void main( String[] args )
    {
        SpringApplication.run(OrderServiceDemo.class,args);

        System.out.println( "Hello World!" );
    }
}
