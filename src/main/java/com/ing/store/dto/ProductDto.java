package com.ing.store.dto;

import com.ing.store.validators.*;


public record ProductDto(Long id,
                         String name,
                         Float price,
                         Integer quantity) {
}
