package com.aquastilo.webapp.interfaces.persistence;

import com.aquastilo.webapp.model.Product;
import com.aquastilo.webapp.model.enums.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductDAO {
    Product createProduct(String name, ProductCategory productCategory);

    Product patchProduct(Product product, String name, String description, Integer price, ProductCategory category);

    Optional<Product> getProduct(long id);

    List<Product> getProducts(ProductCategory category);

    void deleteProduct(Product product);
}
