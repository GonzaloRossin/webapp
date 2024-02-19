package com.aquastilo.webapp.persistence;

import com.aquastilo.webapp.interfaces.persistence.ProductDAO;
import com.aquastilo.webapp.model.Product;
import com.aquastilo.webapp.model.enums.ProductCategory;
import com.aquastilo.webapp.model.enums.status.ProductStatus;
import jakarta.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.aquastilo.webapp.persistence")
@DataJpaTest
public class ProductDaoTest {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private EntityManager em;

    private void setUpData(){
        Product product1 = new Product("salvavidas",
                "muy lindo salvavidas",
                2300,
                ProductCategory.SALVAVIDAS,
                ProductStatus.ACTIVE);
        Product product2 = new Product("remo",
                ProductCategory.REMOS,
                ProductStatus.ACTIVE);

        em.persist(product1);
        em.persist(product2);
        em.flush();
    }

    @Test
    @Rollback
    public void testCreateProduct(){
        Product product = productDAO.createProduct("remo",
                ProductCategory.REMOS);

        Assert.assertNotNull(product);
        Assert.assertEquals("remo", product.getName());
        Assert.assertEquals(ProductCategory.REMOS, product.getProductCategory());
    }

    @Test
    @Rollback
    public void testGetProduct(){
        setUpData();

        Optional<Product> productOptional = productDAO.getProduct( 1L);

        Assert.assertTrue(productOptional.isPresent());
        Product product = productOptional.get();
        Assert.assertEquals("salvavidas", product.getName());
        Assert.assertEquals("muy lindo salvavidas", product.getDescription());
        Assert.assertEquals(2300, product.getPrice().intValue());
        Assert.assertEquals(ProductCategory.SALVAVIDAS, product.getProductCategory());

    }

    @Test
    @Rollback
    public void testPatchProduct(){
        setUpData();
        Optional<Product> productOptional = productDAO.getProduct( 2L);
        Assert.assertTrue(productOptional.isPresent());
        Product product = productOptional.get();
        Assert.assertEquals("remo", product.getName());
        Assert.assertNull(product.getDescription());
        Assert.assertNull(product.getPrice());
        Assert.assertEquals(ProductCategory.REMOS, product.getProductCategory());

        product = productDAO.patchProduct(product, null,
                "salvavidas impecable",
                200,
                null,
                null);
        productOptional = productDAO.getProduct(product.getId());

        Assert.assertTrue(productOptional.isPresent());
        product = productOptional.get();
        Assert.assertEquals(2L, product.getId().longValue());
        Assert.assertEquals("remo", product.getName());
        Assert.assertEquals("salvavidas impecable", product.getDescription());
        Assert.assertEquals(200, product.getPrice().intValue());
        Assert.assertEquals(ProductCategory.REMOS, product.getProductCategory());
    }

    @Test
    @Rollback
    public void testDeleteProduct(){
        setUpData();
        Optional<Product> productOptional = productDAO.getProduct(1L);
        Product product = productOptional.get();
        Assert.assertEquals(ProductStatus.ACTIVE , product.getStatus());

        productDAO.deleteProduct(product);
        productOptional = productDAO.getProduct(product.getId());
        product = productOptional.get();

        Assert.assertEquals(ProductStatus.DELETED , product.getStatus());
    }
}
