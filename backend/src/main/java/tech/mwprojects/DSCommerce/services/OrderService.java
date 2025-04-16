package tech.mwprojects.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.mwprojects.DSCommerce.dto.OrderItemRequestDTO;
import tech.mwprojects.DSCommerce.dto.OrderRequestDTO;
import tech.mwprojects.DSCommerce.dto.OrderResponseDTO;
import tech.mwprojects.DSCommerce.entities.Order;
import tech.mwprojects.DSCommerce.entities.OrderItem;
import tech.mwprojects.DSCommerce.entities.Product;
import tech.mwprojects.DSCommerce.entities.enums.OrderStatus;
import tech.mwprojects.DSCommerce.exceptions.ResourceNotFoundException;
import tech.mwprojects.DSCommerce.repositories.OrderItemRepository;
import tech.mwprojects.DSCommerce.repositories.OrderRepository;
import tech.mwprojects.DSCommerce.repositories.ProductRepository;

import java.time.Instant;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderById(Integer id){
        Order order = repository.getOrderWithItemsById(id).orElseThrow(()->new ResourceNotFoundException("Order with id "+id+" does not exist."));
        authService.validateSelfOrAdmin(order.getClient().getId(), "cannot access another client's order.");
        return new OrderResponseDTO(order);
    }

    @Transactional
    public OrderResponseDTO insert(OrderRequestDTO dto){
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setClient(userService.authenticated());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        for(OrderItemRequestDTO itemDTO : dto.getItems()){
            Product product = productRepository.getReferenceById(itemDTO.getProductId());
            OrderItem item = new OrderItem(product, order, itemDTO.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        Order created = repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderResponseDTO(created);
    }
}
