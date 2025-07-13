package com.ing.store.repositories;

import com.ing.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findByIdAndName(Long id, String name);
    Optional<Product> findByName(String name);
}
