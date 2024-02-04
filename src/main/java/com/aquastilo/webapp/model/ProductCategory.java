package com.aquastilo.webapp.model;

import com.aquastilo.webapp.model.enums.status.ProductCategoryStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "product_category")
public class ProductCategory {
    @Id
    @SequenceGenerator(
            name = "product_category_sequence",
            sequenceName = "product_category_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_category_sequence")
    private Long id;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategoryStatus status;

    public ProductCategory(String name) {
        this.name = name;
    }

    public ProductCategory() {

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

    public ProductCategoryStatus getStatus() {
        return status;
    }

    public void setStatus(ProductCategoryStatus status) {
        this.status = status;
    }
}
