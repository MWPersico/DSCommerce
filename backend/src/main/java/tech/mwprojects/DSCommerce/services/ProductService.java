package tech.mwprojects.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.mwprojects.DSCommerce.dto.ProductDTO;
import tech.mwprojects.DSCommerce.entities.Product;
import tech.mwprojects.DSCommerce.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(Pageable pageable){
    	Page<ProductDTO> result = repository.findAll(pageable).map(ProductDTO::new);
        return result;
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Integer id){
        return new ProductDTO(repository.findById(id).get());
    }
    
    public ProductDTO create(ProductDTO productData) {
    	Product product = new Product(productData);
    	Product createdProduct = repository.save(product);
    	return new ProductDTO(createdProduct);
    }
}
