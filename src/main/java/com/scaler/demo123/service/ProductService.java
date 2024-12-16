package com.scaler.demo123.service;

import com.scaler.demo123.exceptions.ProductNotFoundException;
import com.scaler.demo123.exceptions.ProductNotUpdatedException;
import com.scaler.demo123.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProductById(long id) throws ProductNotFoundException;
    List<Product> getAllProducts();
    Product createProduct(Long id,String name,String description,Double price,String imageUrl,String category);
    Product updateProduct(Long id,Product product) throws ProductNotUpdatedException;
    Product deleteProduct(long id);
}


