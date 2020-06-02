package com.shopping.project.repositories;

import com.shopping.project.entities.Product;
import com.shopping.project.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long id);
    List<Product> findAll();
    List<Product> findAllByProductType(ProductType productType);
    void deleteProductById(Long id);
}
