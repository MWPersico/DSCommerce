package tech.mwprojects.DSCommerce.dto;

import tech.mwprojects.DSCommerce.entities.Product;

import java.io.Serializable;

public class ProductMinResponseDTO implements Serializable {
    private Integer id;
    private String name;
    private Double price;
    private String imageUrl;

    public ProductMinResponseDTO() {
    }

    public ProductMinResponseDTO(Integer id, String name, Double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductMinResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public String getImageUrl(){return imageUrl;}
}
