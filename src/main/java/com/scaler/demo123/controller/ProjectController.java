package com.scaler.demo123.controller;

import com.scaler.demo123.dto.DatabaseProductDto;
import com.scaler.demo123.dto.ErrorDto;
import com.scaler.demo123.exceptions.ProductNotFoundException;
import com.scaler.demo123.exceptions.ProductNotUpdatedException;
import com.scaler.demo123.models.Product;
import com.scaler.demo123.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    // CRUD api's around products

    private ProductService productService;

    public ProjectController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    //@RequestMapping(value ="/products", method = RequestMethod.POST)
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
           Product newProduct = productService.createProduct(product.getTitle(),product.getDescription()
                   ,product.getPrice(),product.getImageUrl(),product.getCategory().getTitle());
           return newProduct;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        System.out.println("Getting all products from DB");
        return productService.getAllProducts();
        //return null;
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
//        //System.out.println("Starting API here");

        Product p = productService.getSingleProductById(id);

        ResponseEntity<Product> responseEntity = new ResponseEntity<>(p, HttpStatus.OK);

        //System.out.println("Ending API here");
        return responseEntity;
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody Product product) throws ProductNotUpdatedException {

           Product p = productService.updateProduct(id,product);
       ResponseEntity<Product> responseEntity = new ResponseEntity<>(p, HttpStatus.CREATED);
       return responseEntity;
        //return null;
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id) {
        System.out.println("Starting API of delete here");

        Product p = productService.deleteProduct(id);

        if(p == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

       ResponseEntity<Product> responseEntity = new ResponseEntity<>(p, HttpStatus.GONE);
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e) {
            ErrorDto dto = new ErrorDto();
            dto.setMessage(e.getMessage());

            ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
            return responseEntity;
    }

   @ExceptionHandler(ProductNotUpdatedException.class)
    public ResponseEntity<ErrorDto> handleProductNotUpdatedException(Exception e) {
        ErrorDto dto = new ErrorDto();
        dto.setMessage(e.getMessage());
        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(dto, HttpStatus.NOT_MODIFIED);
        return responseEntity;
    }

}
