package com.aquastilo.webapp.controller.forms.product;

import com.aquastilo.webapp.model.enums.ProductCategory;

public class CreateProductForm {

    private String name;

    private ProductCategory category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
