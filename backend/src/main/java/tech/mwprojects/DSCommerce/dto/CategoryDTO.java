package tech.mwprojects.DSCommerce.dto;

import tech.mwprojects.DSCommerce.entities.Category;

import java.io.Serializable;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Integer id;
    private final String name;

    public CategoryDTO(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public CategoryDTO(Category category){
        this(category.getId(), category.getName());
    }

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }
}
