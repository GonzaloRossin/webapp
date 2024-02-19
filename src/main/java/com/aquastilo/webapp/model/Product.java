package com.aquastilo.webapp.model;

import com.aquastilo.webapp.model.enums.status.ProductStatus;
import com.aquastilo.webapp.model.enums.ProductCategory;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_sequence")
    private Long id;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            columnDefinition = "TEXT"
    )
    private String description;
    private Integer price;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    private long imageId;

    public Product(String name,
                   String description,
                   Integer price,
                   ProductCategory productCategory,
                   ProductStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productCategory = productCategory;
        this.status = status;
    }

    public Product(String name, ProductCategory productCategory, ProductStatus status) {
        this.name = name;
        this.productCategory = productCategory;
        this.status = status;
    }

    public Product() {
        //for hibernate
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}
