package tech.mwprojects.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.mwprojects.DSCommerce.entities.Order;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("""
        SELECT DISTINCT obj
        FROM Order obj
        JOIN FETCH obj.items i
        JOIN FETCH i.id.product
        LEFT JOIN FETCH obj.payment
        JOIN FETCH obj.client
        WHERE obj.id = :id
    """)
    public Optional<Order> getOrderWithItemsById(Integer id);
}