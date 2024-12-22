package com.scaler.demo123;

import com.scaler.demo123.models.Category;
import com.scaler.demo123.models.Product;
import com.scaler.demo123.repository.CategoryRepository;
import com.scaler.demo123.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Demo123ApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

//    @Test
//    void testQueries(){
//        List<Product> allProducts = productRepository.findByCategoryNative(1l);
//
//        for (Product product : allProducts) {
//            System.out.println(product);
//        }
//    }
//
//    @Test
//    void testQueries2(){
//        Category category = categoryRepository.findCategoryById(1l);
//        System.out.println(category);
//
//        List<Product> allProducts = category.getProducts();
//        System.out.println(allProducts.size());
//        System.out.println("Lets see the size");
//    }
}
