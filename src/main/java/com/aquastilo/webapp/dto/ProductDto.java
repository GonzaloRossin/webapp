package com.aquastilo.webapp.dto;

import com.aquastilo.webapp.model.Product;

import java.net.URI;

public class ProductDto {
    private String name;
    private String description;
    private Integer price;

    private long id;

    private String category;

    private URI self;

    public static ProductDto fromProduct(Product product){
        ProductDto dto = new ProductDto();
        dto.name = product.getName();
        dto.description = product.getDescription();
        dto.price = product.getPrice();
        dto.id = product.getId();
        //TODO: agregar UriInfo
        return dto;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public URI getSelf() {
        return self;
    }

    public void setSelf(URI self) {
        this.self = self;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
