package tech.mwprojects.DSCommerce.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.constraints.*;
import tech.mwprojects.DSCommerce.entities.Category;
import tech.mwprojects.DSCommerce.entities.Product;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotBlank(message = "Campo nome requerido")
    @Size(min = 3, max =80, message = "Nome precisa ter de 3 a 80 caracteres")
    private String name;
    @NotBlank(message = "Campo descrição requerido")
    @Size(min = 10, message = "Descrição precisa ter pelo menos 10 caracteres")
    private String description;
    @NotNull(message = "O campo não pode estar vazio")
    @Positive(message = "O preço deve ser positivo")
    private Double price;
    private String imgUrl;
    @NotEmpty(message = "Deve ter pelo menos uma categoria")
    private Set<CategoryDTO> categories = new HashSet<>();

    public ProductDTO(){}

    public ProductDTO(Integer id, String name, String description, Double price, String imgUrl, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
        this.categories = categories.stream().map(CategoryDTO::new).collect(Collectors.toSet());
    }

    public ProductDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImageUrl(), product.getCategories());
    }

    public String getImgUrl() {
        return imgUrl;
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

    public Set<CategoryDTO> getCategories(){
        return categories;
    }
}
