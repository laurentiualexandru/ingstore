package com.ing.store.requests;

public record ProductRequest (String name,
                             Float price,
                             Integer quantity) {
}
