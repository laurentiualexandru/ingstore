package com.ing.store.controllers;

import com.ing.store.dto.ProductDto;
import com.ing.store.requests.ProductRequest;
import com.ing.store.services.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import validators.AddProductValidator;

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
    public ResponseEntity<ProductDto> addProduct(@Validated @RequestBody ProductRequest addProductRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(productService.addProduct(addProductRequest));
    }

    @InitBinder(value = "addProductRequest")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new AddProductValidator());
    }

}
