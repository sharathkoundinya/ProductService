package org.redlotus.productservice.services;

import org.redlotus.productservice.dtos.ProductRequestDto;
import org.redlotus.productservice.exceptions.InvalidProductIdException;
import org.redlotus.productservice.models.Category;
import org.redlotus.productservice.models.Product;
import org.redlotus.productservice.repositories.CategoryRepository;
import org.redlotus.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
//@Primary
public class SelfProductService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product createProduct(Product product) {
        Category category = product.getCategory();

        if(category.getId() == null){
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        Optional<Product> optionalProduct =  productRepository.findById(id);

        if(optionalProduct.isEmpty()) {
            //throw an exception here --> ProductNotFound
            return null;
        }

        return optionalProduct.get();

        //the whole thing can be replaced with following one-liner
       // return optionalProduct.get(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }
}
