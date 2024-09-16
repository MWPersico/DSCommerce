package tech.mwprojects.DSCommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.mwprojects.DSCommerce.dto.ProductDTO;
import tech.mwprojects.DSCommerce.services.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // parâmetros de URL podem ser passados para paginação: /products?size=10&page=2&sort=name,desc
    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        return service.findAll(pageable);
    }

    @GetMapping(value="/{id}")
    public ProductDTO findById(@PathVariable Integer id){
        return service.findById(id);
    }
    
    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO product) {
    	return service.create(product);
    }
}
