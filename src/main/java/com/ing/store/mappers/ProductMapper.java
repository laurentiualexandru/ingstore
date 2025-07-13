package com.ing.store.mappers;

import com.ing.store.dto.ProductDto;
import com.ing.store.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
}
