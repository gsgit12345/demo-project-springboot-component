package com.demo.project.service;

import com.demo.project.dto.ProductRequest;
import com.demo.project.dto.ProductResponse;
import com.demo.project.model.Product;
import com.demo.project.repository.ProductRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
//@NoArgsConstructor
@Slf4j
@Service
public class ProductService {
    @Autowired(required = true)
    private  ProductRepository departmentRepository;

   // public ProductService()
    //{

    //}
    //public ProductService(ProductRepository repository) {
      // this.repository = repository;
    //} //not required due to @RequiredArgsConstructor

    public  void createProduct(ProductRequest request)
    {
        Product product=Product.builder().name(request.getName()).description(request.getDescription()).
                price(request.getPrice()).build();
        System.out.println("request is::;"+request.toString()+"resposeto::"+departmentRepository.toString());
        departmentRepository.save(product);

        log.info("hello product service "+product.getId());
    }
    public List<ProductResponse> getAllProduct()
    {
    List<Product> productList=    departmentRepository.findAll();
  return   productList.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }
    private ProductResponse mapToProductResponse(Product product)
    {
        return ProductResponse.builder().id(product.getId()).name(product.getName()).
                description(product.getDescription()).price(product.getPrice()).build();
    }
}
