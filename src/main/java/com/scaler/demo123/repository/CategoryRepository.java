package com.scaler.demo123.repository;

import com.scaler.demo123.models.Category;
import com.scaler.demo123.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(Long id);
    Optional<Category> findByTitle(String Title);
    Category save(Category category);
}
