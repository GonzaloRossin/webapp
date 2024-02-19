package com.aquastilo.webapp.interfaces.service;

import com.aquastilo.webapp.model.Product;
import com.aquastilo.webapp.model.enums.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<Product> getProduct(Long id);

    Product createProduct(String productName, ProductCategory category);

    Product patchProduct(Long id,
                         String name,
                         String description,
                         Integer price,
                         ProductCategory category,
                         Long imageId);

    Optional<List<Product>> getProducts(String category);

    void deleteProduct(Long id);

}
