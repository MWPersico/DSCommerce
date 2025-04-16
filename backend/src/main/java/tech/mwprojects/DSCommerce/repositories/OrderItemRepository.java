package tech.mwprojects.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.mwprojects.DSCommerce.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
