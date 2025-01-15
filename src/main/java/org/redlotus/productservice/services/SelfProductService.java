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
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            //throw an exception here --> ProductNotFound
            throw new RuntimeException("Product not found");
        }
        Product currentProduct = optionalProduct.get();
        if(product==null){throw new RuntimeException("Invalid product update sent");}

        if(product.getTitle() != null){
            // if Title is not null, that means we need to update it
            currentProduct.setTitle(product.getTitle());
        }

        if(product.getDescription() != null){
            currentProduct.setDescription(product.getDescription());
        }
        if(product.getCategory() != null){
            currentProduct.setCategory(product.getCategory());
        }
        if(product.getPrice() != 0.0){
            //what if there is a use case to update the price to 0.0?
            currentProduct.setPrice(product.getPrice());
        }
        if(product.getImage() != null){
            currentProduct.setImage(product.getImage());
        }


        return productRepository.save(currentProduct);
    }

    @Override
    public Product replaceProduct(Long id, ProductRequestDto productRequestDto) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }
       else{
           throw new RuntimeException("Product not found");
        }
    }
}
