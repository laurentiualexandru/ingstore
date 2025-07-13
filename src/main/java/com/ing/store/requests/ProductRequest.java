package com.ing.store.requests;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest (@NotBlank(message = "Name is mandatory") String name,
                              Float price,
                              Integer quantity) {

    public ProductRequest {
        if (price == null) {
            price = 0F;
        }
        if (quantity == null) {
            quantity = 0;
        }
    }
}
