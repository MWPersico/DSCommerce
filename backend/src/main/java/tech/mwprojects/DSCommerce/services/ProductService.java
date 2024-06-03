package tech.mwprojects.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.mwprojects.DSCommerce.dto.ProductDTO;
import tech.mwprojects.DSCommerce.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> findAll(){
        return repository.findAll().stream().map(ProductDTO::new).toList();
    }

    public ProductDTO findById(Integer id){
        return new ProductDTO(repository.findById(id).get());
    }
}
