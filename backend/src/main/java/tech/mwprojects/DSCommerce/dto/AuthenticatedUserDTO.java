package tech.mwprojects.DSCommerce.dto;

import jakarta.persistence.*;
import tech.mwprojects.DSCommerce.entities.Role;
import tech.mwprojects.DSCommerce.entities.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthenticatedUserDTO implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birthDate;

    private Set<String> roles = new HashSet<>();

    public AuthenticatedUserDTO(){}

    public AuthenticatedUserDTO(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getPhone(), user.getBirthDate(), user.getRoles());
    }

    public AuthenticatedUserDTO(Integer id, String name, String email, String phone, LocalDate birthDate, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.roles = roles.stream().map(Role::getAuthority).collect(Collectors.toSet());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Set<String> getRoles() {
        return roles;
    }
}
