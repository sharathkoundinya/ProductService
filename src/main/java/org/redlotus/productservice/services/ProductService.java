package org.redlotus.productservice.services;

import org.redlotus.productservice.dtos.ProductRequestDto;
import org.redlotus.productservice.exceptions.InvalidProductIdException;
import org.redlotus.productservice.models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id) throws InvalidProductIdException;
    List<Product> getAllProducts();
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, ProductRequestDto productRequestDto);
    void deleteProduct(Long id);

}
