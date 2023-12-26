package com.devluis.shopping.inventaryservice;

import com.devluis.shopping.inventaryservice.models.entity.Inventory;
import com.devluis.shopping.inventaryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventaryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventaryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args->{
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_14");
			inventory1.setQuantity(100);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone_15");
			inventory2.setQuantity(100);

			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}

}
