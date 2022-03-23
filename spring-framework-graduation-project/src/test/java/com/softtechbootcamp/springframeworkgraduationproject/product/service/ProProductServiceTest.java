package com.softtechbootcamp.springframeworkgraduationproject.product.service;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.ItemNotFoundExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductUpdateDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.entity.ProProduct;
import com.softtechbootcamp.springframeworkgraduationproject.product.service.entityService.ProProductEntityService;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import com.softtechbootcamp.springframeworkgraduationproject.productType.service.entityService.ProProductTypeEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProProductServiceTest {

    @InjectMocks
    private ProProductService proProductService;

    @Mock
    private ProProductEntityService proProductEntityService;

    @Mock
    private ProProductTypeEntityService proProductTypeEntityService;


    @Test
    void shouldFindAllProducts() {
        ProProduct proProduct = mock(ProProduct.class);
        List<ProProduct> proProductList = new ArrayList<>();
        proProductList.add(proProduct);

        ProProductDto proProductDto = mock(ProProductDto.class);
        List<ProProductDto> proProductDtoList = new ArrayList<>();
        proProductDtoList.add(proProductDto);

        when(proProductEntityService.findAll()).thenReturn(proProductList);

        List<ProProductDto> result = proProductService.findAllProducts();
        assertEquals(1,result.size());
    }

    @Test
    void shouldFindAllProductsWhenListIsEmpty(){

        when(proProductEntityService.findAll()).thenReturn(new ArrayList<>());

        List<ProProductDto> result = proProductService.findAllProducts();
        assertEquals(0, result.size());
    }

    @Test
    void shouldSaveProduct() {
        BigDecimal price = BigDecimal.valueOf(5);
        BigDecimal taxRate = BigDecimal.valueOf(1);
        Long id = 6L;

        ProProductSaveDto proProductSaveDto = mock(ProProductSaveDto.class);
        when(proProductSaveDto.getBasicProductPrice()).thenReturn(price);
        when(proProductSaveDto.getProductTypeId()).thenReturn(1L);

        ProProductType proProductType = mock(ProProductType.class);
        when(proProductType.getTaxRate()).thenReturn(BigDecimal.valueOf(1));
        when(proProductTypeEntityService.getIdWithControl(anyLong())).thenReturn(proProductType);

        ProProduct proProduct = mock(ProProduct.class);
        when(proProduct.getId()).thenReturn(id);
        when(proProduct.getTaxRateResult()).thenReturn(taxRate);

        when(proProductEntityService.save(any())).thenReturn(proProduct);

        ProProductDto result = proProductService.saveProduct(proProductSaveDto);

        assertEquals(id, result.getId());
    }

   @Test
    void shouldFindByProductTypeId() {
        Long id = 1L;

        ProProduct proProduct = mock(ProProduct.class);
        when(proProduct.getProductTypeId()).thenReturn(id);
        List<ProProduct> proProductList = new ArrayList<>();
        proProductList.add(proProduct);

        when(proProductEntityService.findByProductTypeId(anyLong())).thenReturn(proProductList);

        ProProductDto proProductDto = mock(ProProductDto.class);
        List<ProProductDto> proProductDtoList = new ArrayList<>();
        proProductDtoList.add(proProductDto);

        List<ProProductDto> result = proProductService.findByProductTypeId(proProductDto.getProductTypeId());

        assertEquals(1, result.size());
    }

    @Test
    void shouldNotFindIfProductTypeIdDoesNotExist() {

        when(proProductEntityService.findByProductTypeId(anyLong())).thenThrow(ItemNotFoundExceptions.class);

        ItemNotFoundExceptions itemNotFoundExceptions = assertThrows(ItemNotFoundExceptions.class, ()-> proProductService.findByProductTypeId(anyLong()));

        verify(proProductEntityService).findByProductTypeId(anyLong());

    }

    @Test
    void shouldUpdateProduct() {
        Long id = 3L;

        ProProductUpdateDto proProductUpdateDto = mock(ProProductUpdateDto.class);
        ProProduct proProduct = mock(ProProduct.class);
        when(proProduct.getId()).thenReturn(id);

        boolean isExist = true;

        when(proProductEntityService.existById(any())).thenReturn(isExist);
        when(proProductEntityService.save(any())).thenReturn(proProduct);


        ProProductDto result = proProductService.updateProduct(proProductUpdateDto);

        assertEquals(id, result.getId());

        verify(proProductEntityService).existById(anyLong());
    }

    @Test
    void shouldNotUpdateProductDoesNotExist() {
        ProProductUpdateDto proProductUpdateDto = mock(ProProductUpdateDto.class);

        when(proProductEntityService.existById(any())).thenThrow(ItemNotFoundExceptions.class);
        ItemNotFoundExceptions itemNotFoundExceptions = assertThrows(ItemNotFoundExceptions.class, ()-> proProductService.updateProduct(proProductUpdateDto));

        verify(proProductEntityService).existById(anyLong());
    }

    @Test
    void shouldUpdateProductPrice() {
        Long id = 4L;
        BigDecimal price = BigDecimal.valueOf(15);

        ProProduct proProduct = mock(ProProduct.class);
        when(proProduct.getId()).thenReturn(id);
        when(proProductEntityService.getIdWithControl(any())).thenReturn(proProduct);

        when(proProduct.getBasicProductPrice()).thenReturn(price);
        when(proProductEntityService.save(any())).thenReturn(proProduct);

        ProProductDto result = proProductService.updateProductPrice(proProduct.getId(), proProduct.getBasicProductPrice());
        assertEquals(BigDecimal.valueOf(15), result.getBasicProductPrice());
    }

    @Test
    void shouldFindAllProductsBetweenPrices(){
        BigDecimal firstPrice = BigDecimal.valueOf(5);
        BigDecimal secondPrice = BigDecimal.valueOf(5000);

        ProProduct proProduct = mock(ProProduct.class);
        List<ProProduct> proProductList = new ArrayList<>();
        proProductList.add(proProduct);

        when(proProductEntityService.findAllByProductBetweenTwoPrices(firstPrice, secondPrice)).thenReturn(proProductList);

        ProProductDto proProductDto = mock(ProProductDto.class);
        List<ProProductDto> proProductDtoList = new ArrayList<>();
        proProductDtoList.add(proProductDto);

        List<ProProductDto> result = proProductService.findAllProductsBetweenTwoPrices(firstPrice, secondPrice);
        assertEquals(proProductList.size(), result.size());
    }

    @Test
    void shouldDeleteProduct() {
        ProProduct proProduct = mock(ProProduct.class);

        when(proProductEntityService.getIdWithControl(any())).thenReturn(proProduct);

        proProductService.deleteProduct(anyLong());

        verify(proProductEntityService).getIdWithControl(anyLong());
    }

    @Test
    void shouldNotDeleteWhenIdDoesNotExist() {

        when(proProductEntityService.getIdWithControl(anyLong())).thenThrow(ItemNotFoundExceptions.class);

        assertThrows(ItemNotFoundExceptions.class, () -> proProductService.deleteProduct(anyLong()));

        verify(proProductEntityService).getIdWithControl(anyLong());
    }

}