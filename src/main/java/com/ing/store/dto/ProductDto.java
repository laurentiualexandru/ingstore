package com.ing.store.dto;

public record ProductDto(Long id,
                         String name,
                         Float price,
                         Integer quantity) {
}
