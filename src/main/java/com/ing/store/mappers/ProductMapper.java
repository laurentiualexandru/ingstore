package com.ing.store.mappers;

import com.ing.store.dto.ProductDto;
import com.ing.store.entities.Product;
import com.ing.store.requests.ProductRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
    Product productRequestToProduct(ProductRequest productRequest);
}
