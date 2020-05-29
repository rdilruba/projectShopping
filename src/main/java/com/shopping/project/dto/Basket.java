package com.shopping.project.dto;


import com.shopping.project.entities.Product;

import java.util.List;

public class Basket {

    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
