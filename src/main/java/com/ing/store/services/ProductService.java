package com.ing.store.services;

import com.ing.store.dto.ProductDto;
import com.ing.store.entities.Product;
import com.ing.store.exceptions.ProductException;
import com.ing.store.exceptions.ProductNotFoundException;
import com.ing.store.mappers.ProductMapper;
import com.ing.store.repositories.ProductRepo;
import com.ing.store.requests.ProductRequest;
import io.vavr.control.Try;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {
    @NonNull
    private ProductRepo productRepo;

    @NonNull
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

    public ProductDto addProduct(ProductRequest productRequest) {
        if (productRepo.findByName(productRequest.name()).isPresent()) {
            throw new ProductException("Product already exists with this name");
        }
        Product product = productMapper.productRequestToProduct(productRequest);
        Product savedProduct = productRepo.saveAndFlush(product);

        return productMapper.productToProductDto(savedProduct);
    }

    public ProductDto patchProductPrice(ProductRequest productRequest) {
        if (productRepo.findByName(productRequest.name()).isEmpty()) {
            throw new ProductException("Product with this name does not exist");
        }
        Product product = productMapper.productRequestToProduct(productRequest);
        Product savedProduct = productRepo.saveAndFlush(product);

        return productMapper.productToProductDto(savedProduct);
    }
}
