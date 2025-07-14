package com.ing.store.services;

import com.ing.store.dto.ProductDto;
import com.ing.store.entities.Product;
import com.ing.store.exceptions.ResourceException;
import com.ing.store.exceptions.ResourceNotFoundException;
import com.ing.store.mappers.ProductMapper;
import com.ing.store.repositories.ProductRepo;
import com.ing.store.requests.ProductRequest;
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

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
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

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> productService.findProduct(name, id));

        assertTrue(exception.getMessage().contains("Id  does not exist"));
    }

    @Test
    void addProduct_whenProductNameAlreadyExists_throwsResourceException() {
        ProductRequest request = new ProductRequest("ExistingProduct", 10.0, 1);

        when(productRepoMock.findByName(request.name())).thenReturn(Optional.of(new Product()));

        ResourceException exception = assertThrows(ResourceException.class,
                () -> productService.addProduct(request));

        assertEquals("Product already exists with this name", exception.getMessage());
    }

    @Test
    void addProduct_whenProductDoesNotExistAndIsNew_savesAndReturnsProductDto() {
        ProductRequest request = new ProductRequest("NewProduct", 15.0, 7);
        Product product = new Product();
        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName(request.name());

        ProductDto productDto = Mockito.mock(ProductDto.class);

        when(productRepoMock.findByName(request.name())).thenReturn(Optional.empty());
        when(productMapperMock.productRequestToProduct(request)).thenReturn(product);
        when(productRepoMock.saveAndFlush(product)).thenReturn(savedProduct);
        when(productMapperMock.productToProductDto(savedProduct)).thenReturn(productDto);

        ProductDto result = productService.addProduct(request);

        assertNotNull(result);
        assertEquals(productDto, result);
        verify(productRepoMock).findByName(request.name());
        verify(productMapperMock).productRequestToProduct(request);
        verify(productRepoMock).saveAndFlush(product);
        verify(productMapperMock).productToProductDto(savedProduct);
    }

    @Test
    void patchProductPrice_whenProductDoesNotExist_throwsProductException() {
        ProductRequest request = new ProductRequest("ProductTest", 20.0, 3);

        when(productRepoMock.findByName(request.name())).thenReturn(Optional.empty());

        ResourceException exception = assertThrows(ResourceException.class,
                () -> productService.patchProductPrice(request));

        assertEquals("Product with this name does not exist", exception.getMessage());
    }

    @Test
    void patchProductPrice_whenProductExists_patchesPriceAndReturnsProductDto() {
        ProductRequest request = new ProductRequest("ProductExist", 25.0, 9);
        Product product = new Product();
        product.setName(request.name());
        product.setPrice(10.0);

        Product savedProduct = new Product();
        savedProduct.setName(request.name());
        savedProduct.setPrice(request.price());

        ProductDto productDto = Mockito.mock(ProductDto.class);

        when(productRepoMock.findByName(request.name())).thenReturn(Optional.of(product));
        when(productRepoMock.saveAndFlush(product)).thenReturn(savedProduct);
        when(productMapperMock.productToProductDto(savedProduct)).thenReturn(productDto);

        ProductDto result = productService.patchProductPrice(request);

        assertNotNull(result);
        assertEquals(productDto, result);
        assertEquals(request.price(), product.getPrice());
        verify(productRepoMock).findByName(request.name());
        verify(productRepoMock).saveAndFlush(product);
        verify(productMapperMock).productToProductDto(savedProduct);
    }

}
