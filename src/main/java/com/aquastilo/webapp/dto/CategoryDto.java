package com.aquastilo.webapp.dto;

import com.aquastilo.webapp.model.enums.ProductCategory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class CategoryDto {
    private String name;

    private URI products;

    public static CategoryDto fromCategory(ProductCategory category){
        CategoryDto dto = new CategoryDto();
        dto.name = category.name();
        dto.products = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/products")
                .queryParam("category", category.name())
                .build()
                .toUri();
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getProducts() {
        return products;
    }

    public void setProducts(URI products) {
        this.products = products;
    }
}
