package com.demo.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfigServer {
    public static void main(String[] args) {

        SpringApplication.run(ConfigServer.class, args);
        System.out.println("Config server started ");

    }
}
//http://localhost:8888/application/default

//this is config server which will interact with git