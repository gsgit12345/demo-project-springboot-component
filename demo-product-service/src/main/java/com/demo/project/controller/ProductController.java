package com.demo.project.controller;

import com.demo.project.dto.ProductRequest;
import com.demo.project.dto.ProductResponse;
import com.demo.project.model.Product;
import com.demo.project.repository.ProductRepository;
import com.demo.project.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

   // @Autowired
    private final ProductService repository;
//http://localhost:8080/api/product
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
        repository.createProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProduct() {
        return repository.getAllProduct();
    }
}
