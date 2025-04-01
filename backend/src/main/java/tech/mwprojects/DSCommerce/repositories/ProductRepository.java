package tech.mwprojects.DSCommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.mwprojects.DSCommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT a FROM Product a WHERE lower(a.name) LIKE lower(concat('%', :name, '%'))")
    Page<Product> searchByName(String name, Pageable pageable);
}
