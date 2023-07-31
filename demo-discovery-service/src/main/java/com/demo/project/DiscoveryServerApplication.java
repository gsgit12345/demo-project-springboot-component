package com.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
//@SpringBootApplication
@EnableEurekaServer
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})

public class DiscoveryServerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(DiscoveryServerApplication.class,args);
        System.out.println( "Eureka server had be up" );
    }
}
