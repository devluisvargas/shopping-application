package com.devluis.shopping.productservice.repository;

import com.devluis.shopping.productservice.models.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
