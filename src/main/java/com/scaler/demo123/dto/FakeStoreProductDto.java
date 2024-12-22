package com.scaler.demo123.dto;

import com.scaler.demo123.models.Category;
import com.scaler.demo123.models.Product;



public class FakeStoreProductDto {
        private Long id;
        private String title;
        private String description;
        private Double price;
        private String category;
        private String image;

        public Product getProduct() {
            Product product = new Product();
            //product.setId(id);
            product.setTitle(title);
            product.setDescription(description);
            product.setPrice(price);
            product.setImageUrl(image);

            Category cat = new Category();
           // cat.setId(id);
            cat.setTitle(category);
            product.setCategory(cat);

            return product;
        }

    public Long getId() {
       return id;
       }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FakeStoreProductDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
