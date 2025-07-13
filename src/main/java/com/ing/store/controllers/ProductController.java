package com.ing.store.controllers;

import com.ing.store.dto.ProductDto;
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
    public ResponseEntity<ProductDto> findProduct(@RequestParam(required = false) String name, @RequestParam(required = true) Long id) {
        ProductDto product = productService.findProduct(name, id);
        return ResponseEntity.ok(product);
    }


}
