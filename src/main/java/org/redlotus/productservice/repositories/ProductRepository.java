package org.redlotus.productservice.repositories;

import lombok.NonNull;
import org.redlotus.productservice.models.Category;
import org.redlotus.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    Optional<Product> findByTitleAndDescription(String title, String description);

    List<Product> findByTitleContaining(String word);

    List<Product> findTopThreeByTitle(String title); //limit the result by 3

    List<Product> findByCategory(Category category);

    void deleteById(Long id);

    @NonNull
    Product save(Product product);

}
