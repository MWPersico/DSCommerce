package tech.mwprojects.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.mwprojects.DSCommerce.dto.OrderResponseDTO;
import tech.mwprojects.DSCommerce.entities.Order;
import tech.mwprojects.DSCommerce.exceptions.ResourceNotFoundException;
import tech.mwprojects.DSCommerce.repositories.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderById(Integer id){
        Order order = repository.getOrderWithItemsById(id).orElseThrow(()->new ResourceNotFoundException("Order with id "+id+" does not exist."));

        return new OrderResponseDTO(order);
    }
}
