package tech.mwprojects.DSCommerce.dto;

import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class OrderRequestDTO implements Serializable {
    @NotEmpty(message = "O pedido deve conter pelo menos um item")
    private Set<OrderItemRequestDTO> items = new HashSet<>();

    public OrderRequestDTO(){}

    public OrderRequestDTO(Set<OrderItemRequestDTO> items){
        this.items = items;
    }

    public Set<OrderItemRequestDTO> getItems() {
        return items;
    }
}
