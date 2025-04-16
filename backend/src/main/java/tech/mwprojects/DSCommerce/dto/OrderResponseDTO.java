package tech.mwprojects.DSCommerce.dto;

import tech.mwprojects.DSCommerce.entities.Order;
import tech.mwprojects.DSCommerce.entities.enums.OrderStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderResponseDTO implements Serializable {
    private Integer id;
    private Instant moment;
    private OrderStatus status;
    private UserMinResponseDTO client;
    private PaymentMinResponseDTO payment;
    private Set<OrderItemResponseDTO> items;
    private Double total;

    public OrderResponseDTO(){}

    public OrderResponseDTO(Integer id, Instant moment, OrderStatus status, UserMinResponseDTO client, PaymentMinResponseDTO payment, Set<OrderItemResponseDTO> items, Double total) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
        this.items = items;
        this.total = total;
    }

    public OrderResponseDTO(Order order){
        this(
                order.getId(),
                order.getMoment(),
                order.getStatus(),
                new UserMinResponseDTO(order.getClient()),
                new PaymentMinResponseDTO(order.getPayment()),
                order.getItems().stream().map(OrderItemResponseDTO::new).collect(Collectors.toSet()),
                order.getTotal()
        );
    }

    public Integer getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public UserMinResponseDTO getClient() {
        return client;
    }

    public PaymentMinResponseDTO getPayment() {
        return payment;
    }

    public Set<OrderItemResponseDTO> getItems() {
        return items;
    }

    public Double getTotal() {
        return total;
    }
}
