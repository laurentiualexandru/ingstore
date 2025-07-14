package com.ing.store.dto;

public record ProductDto(Long id,
                         String name,
                         Double price,
                         Integer quantity) {
}
