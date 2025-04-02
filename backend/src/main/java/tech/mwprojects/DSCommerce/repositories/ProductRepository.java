package tech.mwprojects.DSCommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.mwprojects.DSCommerce.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    static final String WHERE_SEARCH_BY_NAME = " WHERE lower(a.name) LIKE lower(concat('%', :name, '%'))";

    @Query(value = "SELECT DISTINCT a FROM Product a" + WHERE_SEARCH_BY_NAME)
    Page<Product> searchByName(String name, Pageable pageable);

    @Query("SELECT a FROM Product a JOIN FETCH a.categories b WHERE a IN :products")
    List<Product> getProductsCategories(List<Product> products);
}
