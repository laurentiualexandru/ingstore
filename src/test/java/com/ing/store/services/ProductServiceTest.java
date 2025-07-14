package com.ing.store.services;

import com.ing.store.exceptions.ProductNotFoundException;
import com.ing.store.mappers.ProductMapper;
import com.ing.store.repositories.ProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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


}
