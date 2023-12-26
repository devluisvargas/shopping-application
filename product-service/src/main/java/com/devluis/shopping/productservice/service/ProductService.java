package com.devluis.shopping.productservice.service;

import com.devluis.shopping.productservice.models.dto.ProductRequest;
import com.devluis.shopping.productservice.models.dto.ProductResponse;
import com.devluis.shopping.productservice.models.entity.Product;
import com.devluis.shopping.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    public void create(ProductRequest request){
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice()).build();
        productRepository.save(product);

        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAll() {
        return productRepository
                .findAll()
                .stream().map(this::mapToProductResponse)
                .toList();
    }

    private ProductResponse mapToProductResponse(Product e){
        return ProductResponse
                .builder()
                .id(e.getId())
                .name(e.getName())
                .description(e.getDescription())
                .price(e.getPrice())
                .build();
    }

    public ProductResponse findById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return this.mapToProductResponse(product);
    }
}
