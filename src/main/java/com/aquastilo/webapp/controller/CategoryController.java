package com.aquastilo.webapp.controller;

import com.aquastilo.webapp.dto.CategoryDto;
import com.aquastilo.webapp.interfaces.service.ProductCategoryService;
import com.aquastilo.webapp.model.enums.ProductCategory;
import com.aquastilo.webapp.model.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<ProductCategory> categories = pc.getProductCategories();
        List<CategoryDto> dtoList = categories
                .stream()
                .map(CategoryDto::fromCategory)
                .toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(path = "/{category}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("category") String category){
        ProductCategory productCategory = pc.getCategory(category);
        if(productCategory == null){
            throw new CategoryNotFoundException();
        }
        CategoryDto dto = CategoryDto.fromCategory(productCategory);
        return ResponseEntity.ok(dto);
    }
}
