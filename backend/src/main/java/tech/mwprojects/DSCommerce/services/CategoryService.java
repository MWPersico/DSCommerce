package tech.mwprojects.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.mwprojects.DSCommerce.dto.CategoryDTO;
import tech.mwprojects.DSCommerce.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        return repository.findAll().stream().map(CategoryDTO::new).toList();
    }
}
