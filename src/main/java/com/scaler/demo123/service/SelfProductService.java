package com.scaler.demo123.service;

import com.scaler.demo123.dto.DatabaseProductDto;
import com.scaler.demo123.exceptions.ProductNotFoundException;
import com.scaler.demo123.exceptions.ProductNotUpdatedException;
import com.scaler.demo123.models.Category;
import com.scaler.demo123.models.Product;
import com.scaler.demo123.repository.CategoryRepository;
import com.scaler.demo123.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product getSingleProductById(long id) throws ProductNotFoundException {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent())
        {
            return product.get();
        }
        throw new ProductNotFoundException("Product not found in Databsase");
        //return null;
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Product createProduct(String name, String description, Double price, String imageUrl, String category) {
        Product product = new Product();
        //product.setId(id);
        Optional<Category> cat = categoryRepository.findByTitle(category);
        if(cat.isEmpty())
        {
            Category newCat = new Category();
            newCat.setTitle(category);
            Category savedCat = categoryRepository.save(newCat);
            product.setCategory(savedCat);
        }
        else
        {
            product.setCategory(cat.get());
        }
        product.setTitle(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        Product saveProduct =  productRepository.save(product);
        return saveProduct;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotUpdatedException {

        Optional<Product> product1 = productRepository.findById(id);
        if(product1.isPresent())
        {
            Product product2 = product1.get();
            product2.setTitle(product.getTitle());
            product2.setDescription(product.getDescription());
            product2.setPrice(product.getPrice());
            product2.setImageUrl(product.getImageUrl());
            Optional<Category> cat = categoryRepository.findByTitle(product.getCategory().getTitle());
            if(cat.isPresent())
            {
                product2.setCategory(cat.get());
            }
            else
            {
                Category newCat = new Category();
                newCat.setTitle(product.getCategory().getTitle());
                Category savedCat = categoryRepository.save(newCat);
                product2.setCategory(savedCat);
            }
            productRepository.save(product2);
        }
        else {
            throw new ProductNotUpdatedException("Product not found in Databsase");
        }

        return null;
    }

    @Override
    public Product deleteProduct(long id) {
        Optional<Product> product = productRepository.findProductById(id);

        if(product.isPresent())
        {
            productRepository.deleteProductById(id);
            return product.get();
        }
        else
        {
            System.out.println("Product not found in Databsase");
        }

        return null;
    }
}
