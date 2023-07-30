package com.demo.project;

import com.demo.project.dto.ProductRequest;
import com.demo.project.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Container
    public static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.26"))
            .withDatabaseName("Employee")
            .withUsername("root")
            .withPassword("root")
            .waitingFor(Wait.forListeningPort())
            .withEnv("MYSQL_ROOT_HOST", "%");

    @LocalServerPort
    private int port;

    //@Autowired
    // private TestRestTemplate restTemplate;
    @Autowired
    public MockMvc mockMvc;

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
    }
/*

    @AfterEach
    void tearDown() {
        if (dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).close();
        }
    }
*/

    @Test
    public void shouldCreateProduct() throws Exception {
        ProductRequest requestObject = getProductRequest();
        String request = objectMapper.writeValueAsString(requestObject);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product").contentType(MediaType.APPLICATION_JSON).content(request))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1,productRepository.findAll().size());

    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder().name("Ram").description("FirstProduct").price(BigDecimal.valueOf(120000)).build();
    }




/*
    @Test
    public void testCreateUser() {
        Product user = new Product();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("testuser@gmail.com");
        user.setPassword("testpass");

        User response = restTemplate.postForObject("http://localhost:" + port + "/users", user, User.class);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
    }*/
}