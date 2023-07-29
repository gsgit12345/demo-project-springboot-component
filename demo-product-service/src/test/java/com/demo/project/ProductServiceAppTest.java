package com.demo.project;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Unit test for simple App.
 */
//https://www.appsdeveloperblog.com/integration-testing-with-spring-boot-mysql-and-testcontainers/
@SpringBootTest
@Testcontainers
public class ProductServiceAppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    void contextLoads()
    {

    }
    //Testcontainers
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}
