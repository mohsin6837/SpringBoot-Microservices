package com.example.catalog.domain;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PagedResult<ProductEntity> getProducts(int pageNo) {
        Sort sort = Sort.by("name");
        pageNo = pageNo <= 1 ? 0 : pageNo - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, sort);
        var productsPage = productRepository.findAll(pageable);
        PagedResult<ProductEntity> pagedResult = new PagedResult<>(productsPage.getContent(),
                productsPage.getTotalPages(), productsPage.getNumber() + 1, productsPage.isFirst(), productsPage.isLast(),
                productsPage.hasNext(), productsPage.hasPrevious());
        return pagedResult;
    }
}
