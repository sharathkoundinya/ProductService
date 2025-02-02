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
//        Category category = product.getCategory();
//
//        if(category.getId() == null){
//            Category savedCategory = categoryRepository.save(category);
//            product.setCategory(savedCategory);
//        }
        // This code is no longer necessary as the CascadeType.PERSIST in Product.java will take care of it

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
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        Product currentProduct = optionalProduct.get();
//        Long categoryId = currentProduct.getCategory().getId();
//        String currentCategoryTitle = currentProduct.getCategory().getTitle();
        String categoryTitle = productRequestDto.getCategory();
        Optional<Category> optionalCategory = categoryRepository.findByTitle(categoryTitle);
        if(optionalCategory.isEmpty()) {
            //The Category provided is not present in the db, so create a new one
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            Category savedCategory = categoryRepository.save(newCategory);
            currentProduct.setCategory(savedCategory);
        }
        else{
            Category currentCategory = optionalCategory.get();
            currentProduct.setCategory(currentCategory);
        }

        //Set Title
        currentProduct.setTitle(productRequestDto.getTitle());
        currentProduct.setDescription(productRequestDto.getDescription());
        currentProduct.setPrice(productRequestDto.getPrice());
        currentProduct.setImage(productRequestDto.getImage());

        productRepository.save(currentProduct);


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
