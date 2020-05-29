package com.shopping.project.entities;

import com.shopping.project.enums.ProductType;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ProductType productType;
    private double price;
    private String name;
    private String imgUrl;
    private int soldCount;

    public Product() {
        this.soldCount = 0;
    }

    public Product(ProductType productType, double price, String name, String imgUrl) {
        this.productType = productType;
        this.price = price;
        this.name = name;
        this.imgUrl = imgUrl;
        this.soldCount = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(int soldCount) {
        this.soldCount = soldCount;
    }
}
