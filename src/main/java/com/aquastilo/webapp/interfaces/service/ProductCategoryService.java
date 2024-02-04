package com.aquastilo.webapp.interfaces.service;

import com.aquastilo.webapp.model.enums.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategories();

    ProductCategory getCategory(String category);
}
