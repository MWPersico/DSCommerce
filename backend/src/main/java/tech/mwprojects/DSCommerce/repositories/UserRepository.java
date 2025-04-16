package tech.mwprojects.DSCommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tech.mwprojects.DSCommerce.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("""
        SELECT a
        FROM User a
        JOIN FETCH a.roles
        WHERE TRIM(LOWER(a.email)) = TRIM(LOWER(:email))
    """)
    public User searchUserWithRolesByEmail(String email);

    @Query("""
        SELECT a
        FROM User a
        JOIN FETCH a.roles
        WHERE a.id = :id
    """)
    public Optional<User> findByIdWithRoles(Integer id);

    public Optional<User> findByEmail(String email);
}
