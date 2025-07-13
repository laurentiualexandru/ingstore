package com.ing.store.controllers;

import com.ing.store.dto.ProductDto;
import com.ing.store.requests.ProductRequest;
import com.ing.store.services.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    @NonNull
    private ProductService productService;

    @GetMapping(value = "/product")
    public ResponseEntity<ProductDto> findProduct(@RequestParam(required = false) String name, @RequestParam(required = true) Long id) {
        ProductDto product = productService.findProduct(name, id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addProduct(@RequestBody ProductRequest productRequest) {
        return ResponseEntity.ok().build();
    }


}
