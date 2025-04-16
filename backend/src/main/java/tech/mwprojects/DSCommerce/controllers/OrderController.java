package tech.mwprojects.DSCommerce.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.mwprojects.DSCommerce.dto.OrderRequestDTO;
import tech.mwprojects.DSCommerce.dto.OrderResponseDTO;
import tech.mwprojects.DSCommerce.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> findOrderById(@PathVariable Integer id){
        return ResponseEntity.ok(service.getOrderById(id));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @PostMapping
    public ResponseEntity<OrderResponseDTO> findOrderById(@Valid @RequestBody OrderRequestDTO order){
        return ResponseEntity.ok(service.insert(order));
    }
}
