package com.aquastilo.webapp.controller;

import com.aquastilo.webapp.interfaces.service.ProductCategoryService;
import com.aquastilo.webapp.model.enums.ProductCategory;
import com.aquastilo.webapp.model.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {

    private final ProductCategoryService pc;

    @Autowired
    public CategoryController(ProductCategoryService pc) {
        this.pc = pc;
    }

    @GetMapping
    public List<ProductCategory> getCategories(){
        return pc.getProductCategories();
    }

    @GetMapping(path = "/{category}")
    public ProductCategory getCategory(@PathVariable("category") String category){
        ProductCategory productCategory = pc.getCategory(category);
        if(productCategory == null){
            throw new CategoryNotFoundException();
        }
        return productCategory;
    }
}
