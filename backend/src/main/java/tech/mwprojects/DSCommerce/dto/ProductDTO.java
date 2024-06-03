package tech.mwprojects.DSCommerce.dto;

import tech.mwprojects.DSCommerce.entities.Product;

import java.io.Serializable;

public class ProductDTO implements Serializable {
    private static final Long SerialVersionUID = 1L;

    private final Integer id;
    private final String name;
    private final String description;
    private final Double price;
    private final String imageUrl;

    public ProductDTO(Integer id, String name, String description, Double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImageUrl());
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
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
}
