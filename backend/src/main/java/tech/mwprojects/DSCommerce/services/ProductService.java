package tech.mwprojects.DSCommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import tech.mwprojects.DSCommerce.dto.ProductDTO;
import tech.mwprojects.DSCommerce.entities.Product;
import tech.mwprojects.DSCommerce.exceptions.DatabaseException;
import tech.mwprojects.DSCommerce.exceptions.ResourceNotFoundException;
import tech.mwprojects.DSCommerce.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String name, Pageable pageable){
        Page<Product> products = repository.searchByName(name, pageable);
        List<Product> content = products.getContent();

        if(!content.isEmpty()){
            repository.getProductsCategories(content);
        }

        return products.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Integer id){
    	Product product = repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Produto não encontrado"));
    	
        return new ProductDTO(product);
    }
    
    @Transactional
    public ProductDTO create(ProductDTO productData) {
    	Product product = new Product();
    	copyDtoToEntity(productData, product);
    	
    	return new ProductDTO(repository.save(product));
    }
    
    @Transactional
    public ProductDTO update(Integer id, ProductDTO productData) {
    	try {
    		Product product = repository.getReferenceById(id); // não busca no banco de dados, mas é monitorado pela JPA
        	copyDtoToEntity(productData, product);
        	return new ProductDTO(repository.save(product));
    	}catch(EntityNotFoundException ex) {throw new ResourceNotFoundException("Produto não encontrado para atualização");}
    }
    
    
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Integer id) {
    	if(!repository.existsById(id)) {throw new ResourceNotFoundException("Produto não encontrado para deleção");}
    	
    	try{
    		repository.deleteById(id);    		
    	}catch(DataIntegrityViolationException ex) {
    		throw new DatabaseException("Falha de integridade referencial");
    	}
    }
    
    private void copyDtoToEntity(ProductDTO dto, Product entity) {
    	entity.setName(dto.getName());
    	entity.setPrice(dto.getPrice());
    	entity.setDescription(dto.getDescription());
    	entity.setImageUrl(dto.getImageUrl());
    }
}
