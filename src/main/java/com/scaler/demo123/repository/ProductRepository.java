package com.scaler.demo123.repository;

import com.scaler.demo123.models.Category;
import com.scaler.demo123.models.Product;
import com.scaler.demo123.repository.Projections.ProductProjections;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //Product findByName(String name);
    Product save(Product product);
    Product findByTitle(String title);
    Optional<Product> findProductById(Long id);
    @Transactional
    void deleteProductById(Long id);



   // Product findById();

    @Query("select p from Product p where p.category.id =:categoryId ")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "select * from Product p where p.category_id =:categoryId ",nativeQuery = true)
    List<Product> findByCategoryNative(@Param("categoryId") Long categoryId);

    @Query("select p.title as title,p.description as description from Product p where p.category.id =:categoryId ")
    List<ProductProjections> findByCategoryUsingProjections(@Param("categoryId") Long categoryId);

    @Query(value = "select  * from Product ",nativeQuery = true)
    List<Product> findAll();

    @Query(value = "UPDATE product SET p.title = pro.getTitle,p.des",nativeQuery = true)
    Boolean update(@Param("id") Long id,@Param("pro") Product product);
}
