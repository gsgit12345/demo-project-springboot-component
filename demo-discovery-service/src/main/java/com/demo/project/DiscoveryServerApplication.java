package com.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 */

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
@EnableEurekaServer

//@ComponentScan
public class DiscoveryServerApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(DiscoveryServerApplication.class, args);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Eureka server had be up");
    }
/*   @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }
    }*/
}
