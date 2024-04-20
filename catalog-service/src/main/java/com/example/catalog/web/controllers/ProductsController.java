package com.example.catalog.web.controllers;

import com.example.catalog.domain.PagedResult;
import com.example.catalog.domain.Product;
import com.example.catalog.domain.ProductNotFoundException;
import com.example.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductService productsService;

    public ProductsController(ProductService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public PagedResult<Product> getProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        return productsService.getProducts(pageNo);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return productsService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.forCode(code));
    }
}
