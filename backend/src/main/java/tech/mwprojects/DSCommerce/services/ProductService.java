package tech.mwprojects.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.mwprojects.DSCommerce.dto.ProductDTO;
import tech.mwprojects.DSCommerce.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    // parâmetros de URL podem ser passados para paginação: /products?size=10&page=2&sort=name,desc
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
    	Page<ProductDTO> result = repository.findAll(pageable).map(ProductDTO::new);
        return result;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Integer id){
        return new ProductDTO(repository.findById(id).get());
    }
}
