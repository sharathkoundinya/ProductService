package org.redlotus.productservice;

import org.junit.jupiter.api.Test;
import org.redlotus.productservice.models.Product;
import org.redlotus.productservice.projections.ProductWithIdAndTitle;
import org.redlotus.productservice.repositories.CategoryRepository;
import org.redlotus.productservice.repositories.ProductRepository;
import org.redlotus.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testQueries(){
        List<ProductWithIdAndTitle> products = productRepository.someRandomQuery();
        for(ProductWithIdAndTitle product : products){
            System.out.println(product.getTitle());
            System.out.println(product.getId());
        }
        System.out.println(products);

        categoryRepository.deleteById(202L);
    }

}
