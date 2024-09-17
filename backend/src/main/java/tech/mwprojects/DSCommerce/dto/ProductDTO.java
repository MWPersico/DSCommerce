package tech.mwprojects.DSCommerce.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import tech.mwprojects.DSCommerce.entities.Product;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Integer id;
    @NotBlank(message = "Campo nome requerido")
    @Size(min = 3, max =80, message = "Nome precisa ter de 3 a 80 caracteres")
    private final String name;
    @NotBlank(message = "Campo descrição requerido")
    @Size(min = 10, message = "Descrição precisa ter pelo menos 10 caracteres")
    private final String description;
    @Positive(message = "O preço deve ser positivo")
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
