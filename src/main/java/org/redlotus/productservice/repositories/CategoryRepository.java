package org.redlotus.productservice.repositories;

import org.redlotus.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);

    Optional<Category> findByTitle(String title);

    void deleteById(Long id);
}
