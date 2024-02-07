package com.aquastilo.webapp.persistence;

import com.aquastilo.webapp.interfaces.persistence.ProductDAO;
import com.aquastilo.webapp.model.Product;
import com.aquastilo.webapp.model.User;
import com.aquastilo.webapp.model.enums.ProductCategory;
import com.aquastilo.webapp.model.enums.status.ProductStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductJpaDao implements ProductDAO {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Product createProduct(String name, ProductCategory category) {
        final Product product = new Product(name, category, ProductStatus.ACTIVE);
        em.persist(product);
        return product;
    }

    @Transactional
    @Override
    public Product patchProduct(Product product,
                                String name,
                                String description,
                                Integer price,
                                ProductCategory category) {
        if (name != null) {
            product.setName(name);
        }
        if (description != null) {
            product.setDescription(description);
        }
        if (price != null && price > 0) {
            product.setPrice(price);
        }
        if( category != null ){
            product.setProductCategory(category);
        }
        em.merge(product);
        return product;
    }

    @Override
    public Optional<Product> getProduct(long id) {
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p WHERE p.id = :id AND p.status = 'ACTIVE'",Product.class)
                               .setParameter("id", id);
        try {
            Product product = query.getSingleResult();
            return Optional.ofNullable(product);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> getProducts(ProductCategory category) {
        TypedQuery<Product> query =
                em.createQuery("SELECT p FROM Product p WHERE p.productCategory = :category AND p.status = 'ACTIVE'", Product.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deleteProduct(Product product) {
        product.setStatus(ProductStatus.DELETED);
        em.merge(product);
    }
}
