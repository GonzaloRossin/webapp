package com.aquastilo.webapp.controller;

import com.aquastilo.webapp.controller.forms.product.CreateProductForm;
import com.aquastilo.webapp.controller.forms.product.PatchProductForm;
import com.aquastilo.webapp.interfaces.service.ProductService;
import com.aquastilo.webapp.model.Product;
import com.aquastilo.webapp.model.enums.ProductCategory;
import com.aquastilo.webapp.model.exceptions.ProductNotFoundException;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService pd;

    @Autowired
    public ProductController(ProductService pd) {
        this.pd = pd;
    }

    @GetMapping(path = "/{id}")
    public Product getProduct(@PathVariable String id){
        Optional<Product> productOptional = pd.getProduct(Long.parseLong(id));
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException();
        }
        return productOptional.get();
    }

    @PostMapping
    public Product postProduct(@RequestBody CreateProductForm form){
        return pd.createProduct(form.getName(), form.getCategory());
    }

    @PatchMapping
    public Product patchProduct(@RequestBody PatchProductForm form){
        Product product = pd.patchProduct(
                form.getId(),
                form.getProductName(),
                form.getDescription(),
                form.getPrice(),
                form.getCategory());
        if(product == null){
            throw new ProductNotFoundException();
        }
        return product;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam("category")String category){
        return pd.getProducts(category);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable String id){
        pd.deleteProduct(Long.parseLong(id));
    }
}
