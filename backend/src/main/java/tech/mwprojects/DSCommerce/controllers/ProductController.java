package tech.mwprojects.DSCommerce.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.mwprojects.DSCommerce.dto.ProductDTO;
import tech.mwprojects.DSCommerce.services.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // Parâmetros de URL podem ser passados para paginação: /products?size=10&page=2&sort=name,desc
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable)) ;
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO product) {
    	ProductDTO created = service.create(product);
    	URI uri = ServletUriComponentsBuilder
    			.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(created.getId())
    			.toUri();
    	
    	// Retorna o produto criado e o header Location com URL do recurso
    	return ResponseEntity.created(uri).body(created);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody ProductDTO product) {
    	ProductDTO updated = service.update(id, product);
    	
    	return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
    	service.delete(id);
    	return ResponseEntity.noContent().build();
    }
}
