package com.aquastilo.webapp.service;

import com.aquastilo.webapp.interfaces.service.ProductCategoryService;
import com.aquastilo.webapp.model.enums.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductCatServiceImpl implements ProductCategoryService {
    @Override
    public List<ProductCategory> getProductCategories() {
        return Arrays.stream(ProductCategory.values()).toList();
    }

    @Override
    public ProductCategory getCategory(String category) {
        try {
            return ProductCategory.valueOf(category);
        }catch (IllegalArgumentException e){
            return null;
        }
    }
}
