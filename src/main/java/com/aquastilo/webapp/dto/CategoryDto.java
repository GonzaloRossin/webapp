package com.aquastilo.webapp.dto;

import com.aquastilo.webapp.model.enums.ProductCategory;

import java.net.URI;

public class CategoryDto {
    private String name;
    private URI self;

    private URI products;

    public static CategoryDto fromCategory(ProductCategory category){
        CategoryDto dto = new CategoryDto();
        dto.name = category.name();
        //TODO: agregar URI info
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getSelf() {
        return self;
    }

    public void setSelf(URI self) {
        this.self = self;
    }

    public URI getProducts() {
        return products;
    }

    public void setProducts(URI products) {
        this.products = products;
    }
}
