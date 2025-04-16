package tech.mwprojects.DSCommerce.dto;

import tech.mwprojects.DSCommerce.entities.OrderItem;

import java.io.Serializable;

public class OrderItemResponseDTO implements Serializable {
    private Integer productId;
    private String name;
    private Double price;
    private Integer quantity;
    private Double subtotal;
    private String imgUrl;

    public OrderItemResponseDTO(){}

    public OrderItemResponseDTO(Integer productId, String name, Double price, Integer quantity, Double subtotal, String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.imgUrl = imgUrl;
    }

    public OrderItemResponseDTO(OrderItem orderItem){
        this(
                orderItem.getProduct().getId(),
                orderItem.getProduct().getName(),
                orderItem.getPrice(),
                orderItem.getQuantity(),
                orderItem.getSubtotal(),
                orderItem.getProduct().getImageUrl()
        );
    }

    public Integer getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public String getImgUrl(){return imgUrl;}
}
