package com.shopping.project.services;

import com.shopping.project.entities.Product;
import com.shopping.project.enums.ProductType;

import java.util.List;


public interface ProductService {
    Product getProduct(Long id);

    List<Product> getAllProducts();

    Product saveProduct(Product item);

    List<Product> getAllProductsType(ProductType productType);

    void deleteProduct(Long id);
}
