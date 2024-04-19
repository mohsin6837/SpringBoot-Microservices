package com.example.catalog.web.controllers;

import com.example.catalog.domain.PagedResult;
import com.example.catalog.domain.ProductEntity;
import com.example.catalog.domain.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductService productsService;

    public ProductsController(ProductService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public PagedResult<ProductEntity> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productsService.getProducts(pageNo);
    }
}
