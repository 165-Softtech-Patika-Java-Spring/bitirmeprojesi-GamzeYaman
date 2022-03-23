package com.softtechbootcamp.springframeworkgraduationproject.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtechbootcamp.springframeworkgraduationproject.general.BaseTest;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductSaveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProProductControllerTest extends BaseTest {

    private static final String BASE_PATH = "/products";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void shouldFindAllProducts() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH).content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void shouldFindProductsByProductTypeId() throws Exception {
        MvcResult result = mockMvc.perform(
                get(BASE_PATH + "/product-type-id/1").content("1L").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldSaveProduct() throws Exception {
        ProProductSaveDto proProductSaveDto = ProProductSaveDto.builder()
                .productName("Apple").basicProductPrice(BigDecimal.valueOf(5)).productTypeId(1L).build();

        String content = objectMapper.writeValueAsString(proProductSaveDto);

        MvcResult result = mockMvc.perform(
                post(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void getProductBetweenTwoPrices() throws Exception {

        MvcResult result = mockMvc.perform(
                get(BASE_PATH+ "/between-prices?firstPrice=5&secondPrice=50000").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }
    @Test
    void shouldUpdateProduct() throws Exception{
        ProProductDto proProductDto = new ProProductDto();
        proProductDto.setId(3L);
        proProductDto.setProductName("Test2");
        proProductDto.setBasicProductPrice(BigDecimal.valueOf(150));
        proProductDto.setProductTypeId(1L);

        String content = objectMapper.writeValueAsString(proProductDto);

        MvcResult result = mockMvc.perform(
                put(BASE_PATH).content(content).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldUpdateProductPrice() throws Exception {
        MvcResult result = mockMvc.perform(
                patch(BASE_PATH+ "?id=7&price=50").content("").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);

    }

    @Test
    void shouldDeleteProduct() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH+ "/12").content("12").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertTrue(isSuccess);
    }

    @Test
    void shouldNotDeleteWhenIdDoesNotExist() throws Exception {

        MvcResult result = mockMvc.perform(
                delete(BASE_PATH + "/9999").content("9999").contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();

        boolean isSuccess = isSuccess(result);

        assertFalse(isSuccess);
    }
}