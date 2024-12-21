package org.redlotus.productservice.controllers;

import org.redlotus.productservice.models.Product;
import org.redlotus.productservice.services.FakeStoreProductService;
import org.redlotus.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }


    // localhost:2024/products/124
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {

        return productService.getProductById(id);
        //return new Product();
    }

    // localhost:2024/products
    @GetMapping
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    //Create Product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return new Product();
    }

    // Partial Update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    // Complete replacement
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return new Product();
    }

    // Delete Product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        return;
    }





}
