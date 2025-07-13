package com.ing.store.controllers;

import com.ing.store.entities.Product;
import com.ing.store.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/productSearch")
    public ResponseEntity<Product> findProduct(@RequestParam(required = true) String name, @RequestParam(required = false) Long id) {
        Product product = productService.findProduct(name, id);

        return ResponseEntity.ok(product);
    }


}
