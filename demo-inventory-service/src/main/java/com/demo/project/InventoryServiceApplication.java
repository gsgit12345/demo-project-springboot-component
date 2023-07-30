package com.demo.project;

import com.demo.project.model.Inventory;
import com.demo.project.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class InventoryServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(InventoryServiceApplication.class,args);
        System.out.println( "Hello World!" );
    }
    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository)
    {
        return args -> {
            Inventory inventory=new Inventory();
            inventory.setSkuCode("redmi");
            inventory.setQuantity(100);
            Inventory inventory2=new Inventory();
            inventory2.setSkuCode("samsung");
            inventory2.setQuantity(200);
            inventoryRepository.save(inventory);
            inventoryRepository.save(inventory2);

        };
    }
}
