package com.shopping.project.controller;

import com.shopping.project.entities.Product;
import com.shopping.project.enums.ProductType;
import com.shopping.project.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PostMapping("")
    public @ResponseBody ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.saveProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @GetMapping("")
    public @ResponseBody ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public @ResponseBody ResponseEntity<List<Product>> getProductsOfType(@PathVariable int categoryId) {
        ProductType productType = ProductType.values()[categoryId];
        return new ResponseEntity<>(productService.getAllProductsType(productType), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public @ResponseBody ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
