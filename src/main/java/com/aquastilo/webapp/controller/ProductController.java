package com.aquastilo.webapp.controller;

import com.aquastilo.webapp.controller.forms.product.CreateProductForm;
import com.aquastilo.webapp.controller.forms.product.PatchProductForm;
import com.aquastilo.webapp.dto.ProductDto;
import com.aquastilo.webapp.interfaces.service.ProductService;
import com.aquastilo.webapp.model.Product;
import com.aquastilo.webapp.model.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ProductDto> getProduct(@PathVariable String id){
        Optional<Product> productOptional = pd.getProduct(Long.parseLong(id));
        if(productOptional.isPresent()){
            return ResponseEntity.ok(ProductDto.fromProduct(productOptional.get()));
        }
        else{
            throw new ProductNotFoundException();
        }
    }

    @PostMapping
    public ResponseEntity<ProductDto> postProduct(@RequestBody CreateProductForm form){
        Product product = pd.createProduct(
                form.getName(),
                form.getDescription(),
                form.getPrice(),
                form.getCategory());
        ProductDto productDto = ProductDto.fromProduct(product);

        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<ProductDto> patchProduct(@RequestBody PatchProductForm form, @PathVariable Long id){
        Product product = pd.patchProduct(
                id,
                form.getProductName(),
                form.getDescription(),
                form.getPrice(),
                form.getCategory(),
                form.getImageId());
        if(product == null){
            throw new ProductNotFoundException();
        }

        ProductDto productDto = ProductDto.fromProduct(product);

        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam("category")String category){
        Optional<List<Product>> listOptional = pd.getProducts(category);
        if(listOptional.isEmpty()){
            return ResponseEntity.badRequest().body("Invalid category");
        }
        List<ProductDto> listDto = listOptional.get().stream().map(ProductDto::fromProduct).toList();

        return ResponseEntity.ok(listDto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        pd.deleteProduct(Long.parseLong(id));

        return ResponseEntity.noContent().build();
    }
}
