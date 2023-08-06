package com.demo.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Hello world!
 */
@SpringBootApplication
@Slf4j
@EnableEurekaClient
public class NotificationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);

        System.out.println("NotificationServiceApplication had been started");
    }

    @KafkaListener(topics = "notificationTopic")
    public void handleNotification(OrderPlacedEvent orderPlacedEvent) {
        log.info("received notification  for order-{} " + orderPlacedEvent.getOrderNumber());
    }
}
