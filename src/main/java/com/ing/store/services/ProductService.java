package com.ing.store.services;

import com.ing.store.dto.ProductDto;
import com.ing.store.entities.Product;
import com.ing.store.exceptions.ProductNotFoundException;
import com.ing.store.mappers.ProductMapper;
import com.ing.store.repositories.ProductRepo;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {
    private ProductRepo productRepo;
    private ProductMapper productMapper;

    public ProductDto findProduct(String name, Long id) {
        Product product;
        if (!Objects.isNull(name)) {
            product = productRepo.findByIdAndName(id, name).orElseThrow(() -> new ProductNotFoundException("Id with the given name does not exist"));
        } else {
            product = Try.of(() -> productRepo.getReferenceById(id)).
                    getOrElseThrow((Throwable ex) -> {
                        throw new ProductNotFoundException("Id  does not exist", ex);
                    });
        }
        return productMapper.productToProductDto(product);
    }

}
