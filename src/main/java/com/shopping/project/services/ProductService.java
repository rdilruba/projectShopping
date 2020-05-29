package com.shopping.project.services;

import com.shopping.project.entities.Product;

import java.util.List;


public interface ProductService {
    Product getProduct(Long id);

    List<Product> getAllProducts();

    Product saveProduct(Product item);

}
