package tech.mwprojects.DSCommerce.dto;

import tech.mwprojects.DSCommerce.entities.User;

import java.io.Serializable;

public class UserMinResponseDTO implements Serializable {
    private Integer id;
    private String name;

    public UserMinResponseDTO(){}

    public UserMinResponseDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserMinResponseDTO(User user){
        this(user.getId(), user.getName());
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
