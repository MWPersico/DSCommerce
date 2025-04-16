package tech.mwprojects.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.mwprojects.DSCommerce.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
