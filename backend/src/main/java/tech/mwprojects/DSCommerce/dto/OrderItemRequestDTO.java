package tech.mwprojects.DSCommerce.dto;

import java.io.Serializable;

public class OrderItemRequestDTO implements Serializable {
    private Integer productId;
    private Integer quantity;

    public OrderItemRequestDTO(){}

    public OrderItemRequestDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
