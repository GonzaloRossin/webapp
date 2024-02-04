package com.aquastilo.webapp.service;

import com.aquastilo.webapp.interfaces.persistence.ProductDAO;
import com.aquastilo.webapp.interfaces.service.ProductCategoryService;
import com.aquastilo.webapp.interfaces.service.ProductService;
import com.aquastilo.webapp.model.Product;
import com.aquastilo.webapp.model.enums.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO pd;

    private final ProductCategoryService pc;

    @Autowired
    public ProductServiceImpl(ProductDAO pd, ProductCategoryService pc) {
        this.pd = pd;
        this.pc = pc;
    }


    @Override
    public Optional<Product> getProduct(Long id) {
        return pd.getProduct(id);
    }

    @Transactional
    @Override
    public Product createProduct(String productName, ProductCategory category) {
        return pd.createProduct(productName, category);
    }

    @Transactional
    @Override
    public Product patchProduct(Long id,
                                String name,
                                String description,
                                Integer price,
                                ProductCategory category) {
        Optional<Product> productOptional = pd.getProduct(id);
        return productOptional.map(product -> pd.patchProduct(product, name, description, price, category))
                .orElse(null);
    }

    @Override
    public List<Product> getProducts(String category) {
        if(pc.getCategory(category) == null){
            return new ArrayList<>();
        }
        return pd.getProducts(ProductCategory.valueOf(category));
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> productOptional = pd.getProduct(id);
        productOptional.ifPresent(pd::deleteProduct);
    }
}