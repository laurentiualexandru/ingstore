package com.ing.store.services;

import com.ing.store.dto.ProductDto;
import com.ing.store.entities.Product;
import com.ing.store.exceptions.ProductNotFoundException;
import com.ing.store.mappers.ProductMapper;
import com.ing.store.repositories.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepo productRepoMock;

    @Mock
    private ProductMapper productMapperMock;

    @InjectMocks
    private ProductService productService;

    @Test
    void findProduct_byNameAndId_whenProductNotFound_throwsProductNotFoundException() {
        String name = "Product1";
        Long id = 1L;

        when(productRepoMock.findByIdAndName(id, name)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                () -> productService.findProduct(name, id));

        assertEquals("Id with the given name does not exist", exception.getMessage());
    }

    @Test
    void findProduct_byNameAndId_whenProductExists_returnsProductDto() {
        String name = "Product2";
        Long id = 2L;

        Product product = new Product();
        product.setId(id);
        product.setName(name);

        ProductDto productDto = Mockito.mock(ProductDto.class);

        when(productRepoMock.findByIdAndName(id, name)).thenReturn(Optional.of(product));
        when(productMapperMock.productToProductDto(product)).thenReturn(productDto);

        ProductDto result = productService.findProduct(name, id);

        assertNotNull(result);
        verify(productRepoMock).findByIdAndName(id, name);
        verify(productMapperMock).productToProductDto(product);
    }

    @Test
    void findProduct_byId_whenProductNotFound_throwsProductNotFoundException() {
        String name = null;
        Long id = 1L;

        when(productRepoMock.getReferenceById(id)).thenThrow(new EntityNotFoundException());

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                () -> productService.findProduct(name, id));

        assertTrue(exception.getMessage().contains("Id  does not exist"));
    }


}
