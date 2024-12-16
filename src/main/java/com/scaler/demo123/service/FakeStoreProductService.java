package com.scaler.demo123.service;

import com.scaler.demo123.dto.FakeStoreProductDto;
import com.scaler.demo123.exceptions.ProductNotFoundException;
import com.scaler.demo123.exceptions.ProductNotUpdatedException;
import com.scaler.demo123.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProductById(long id) throws ProductNotFoundException {

        //System.out.println("Inside fakestore product service");
        FakeStoreProductDto fakeStoreProductDto =  restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        //System.out.println(fakeStoreProductDto.toString());

        if(fakeStoreProductDto == null)
        {
            throw new ProductNotFoundException("Product not found");
        }
        return fakeStoreProductDto.getProduct();
    }

    @Override
    public List<Product> getAllProducts() {

        System.out.println("we are in list product implementation ");
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();

        List<Product> products = new ArrayList<>();
        for(int i = 0; i < fakeStoreProductDtos.length; i++)
        {
            products.add(fakeStoreProductDtos[i].getProduct());
        }

        return products;
    }

    @Override
    public Product createProduct(Long id, String name, String description, Double price, String imageUrl, String category) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(name);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setCategory(category);

        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.postForObject("https://fakestoreapi.com/products",
                fakeStoreProductDto, FakeStoreProductDto.class);

        return fakeStoreProductDto1.getProduct();
    }

    @Override
    public Product updateProduct(Long id,Product product) throws ProductNotUpdatedException {
        FakeStoreProductDto fakeStoreProductDtOld = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        System.out.println("Printing old values");
        System.out.println(fakeStoreProductDtOld.toString());

        FakeStoreProductDto fakeStoreProductDto1 = new FakeStoreProductDto();
        fakeStoreProductDto1.setId(product.getId());
        fakeStoreProductDto1.setTitle(product.getTitle());
        fakeStoreProductDto1.setDescription(product.getDescription());
        fakeStoreProductDto1.setPrice(product.getPrice());
        fakeStoreProductDto1.setImage(product.getImageUrl());
        fakeStoreProductDto1.setCategory(product.getCategory().getTitle());

        restTemplate.put("https://fakestoreapi.com/products/" + id, fakeStoreProductDto1);
        System.out.println("Printing new values");
        System.out.println(fakeStoreProductDto1.toString());
        return fakeStoreProductDto1.getProduct();
    }

    @Override
    public Product deleteProduct(long id) {
        System.out.println("Inside fakestore product service to delete product");
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        System.out.println("Product deleted successfully");
        return fakeStoreProductDto.getProduct();
    }

}
