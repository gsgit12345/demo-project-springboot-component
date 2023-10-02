package com.demo.project;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
public class DatabaseTestConfiguration {

    @Bean
    public MySQLContainer<?> mySQLContainer() {
        MySQLContainer<?> container = new MySQLContainer<>(DockerImageName.parse("gshukla123/mysql:latest"))
                .withDatabaseName("product")
                .withUsername("root")
                .withPassword("root");
        container.start();
        return container;
    }
}