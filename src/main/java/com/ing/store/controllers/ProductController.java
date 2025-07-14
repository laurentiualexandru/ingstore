package com.ing.store.controllers;

import com.ing.store.dto.ProductDto;
import com.ing.store.requests.ProductRequest;
import com.ing.store.services.ProductService;
import com.ing.store.validators.AddGroup;
import com.ing.store.validators.PatchGroup;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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

    @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@Validated(AddGroup.class) @RequestBody ProductRequest addProductRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(productService.addProduct(addProductRequest));
    }

    @PatchMapping("/productPrice")
    public ResponseEntity<ProductDto> updateProductPrice(@Validated(PatchGroup.class) @RequestBody ProductRequest patchProductRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(productService.patchProductPrice(patchProductRequest));
    }
}
