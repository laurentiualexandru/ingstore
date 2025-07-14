package com.ing.store.services;

import com.ing.store.dto.ProductDto;
import com.ing.store.entities.Product;
import com.ing.store.exceptions.ResourceException;
import com.ing.store.exceptions.ResourceNotFoundException;
import com.ing.store.mappers.ProductMapper;
import com.ing.store.repositories.ProductRepo;
import com.ing.store.requests.ProductRequest;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public ProductDto findProduct(String name, Long id) {
        Product product;
        if (!Objects.isNull(name)) {
            product = productRepo.findByIdAndName(id, name).orElseThrow(() -> new ResourceNotFoundException("Id with the given name does not exist"));
        } else {
            product = Try.of(() -> productRepo.getReferenceById(id)).
                    getOrElseThrow((Throwable ex) -> {
                        throw new ResourceNotFoundException("Id  does not exist", ex);
                    });
        }
        log.info("Product with name: {} was found.", name);

        return productMapper.productToProductDto(product);
    }

    public ProductDto addProduct(ProductRequest productRequest) {
        if (productRepo.findByName(productRequest.name()).isPresent()) {
            throw new ResourceException("Product already exists with this name");
        }
        Product product = productMapper.productRequestToProduct(productRequest);
        Product savedProduct = productRepo.saveAndFlush(product);
        log.info("Product with name: {} was added . Id: {}", savedProduct.getName(), savedProduct.getId());
        return productMapper.productToProductDto(savedProduct);
    }

    public ProductDto patchProductPrice(ProductRequest productRequest) {
        Optional<Product> product = productRepo.findByName(productRequest.name());
        if (product.isEmpty()) {
            throw new ResourceException("Product with this name does not exist");
        }
        product.get().setPrice(productRequest.price());
        Product savedProduct = productRepo.saveAndFlush(product.get());
        log.info("Product with name: {} was patched.", savedProduct.getName());

        return productMapper.productToProductDto(savedProduct);
    }
}
