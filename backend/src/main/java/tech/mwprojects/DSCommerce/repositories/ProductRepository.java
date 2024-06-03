package tech.mwprojects.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.mwprojects.DSCommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
