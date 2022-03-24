package com.softtechbootcamp.springframeworkgraduationproject.product.controller;

import com.softtechbootcamp.springframeworkgraduationproject.general.dto.RestResponse;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductUpdateDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.service.ProProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProProductController {
    private final ProProductService proProductService;

    @Operation(
            tags = "Product Controller",
            description = "List all products"
    )
    @GetMapping
    public ResponseEntity findAllProducts(){
        List<ProProductDto> proProductDtoList = proProductService.findAllProducts();
        return ResponseEntity.ok(RestResponse.of(proProductDtoList));
    }

    @Operation(
            tags = "Product Controller",
            description = "List products according to product type"
    )
    @GetMapping("/product-type-id/{productTypeId}")
    public ResponseEntity findProductsByProductTypeId(@PathVariable Long productTypeId){
        List<ProProductDto> proProductDtoList = proProductService.findByProductTypeId(productTypeId);
        return ResponseEntity.ok(RestResponse.of(proProductDtoList));
    }

    @Operation(
            tags = "Product Controller",
            description = "List all products between two prices"
    )
    @GetMapping("/between-prices")
    public ResponseEntity getProductsBetweenTwoPrices(@RequestParam BigDecimal firstPrice, @RequestParam BigDecimal secondPrice){
       List<ProProductDto> proProductDto = proProductService.findAllProductsBetweenTwoPrices(firstPrice, secondPrice);
        return ResponseEntity.ok(RestResponse.of(proProductDto));
    }

    @Operation(
            tags = "Product Controller",
            description = "Create new product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Products",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ProProductSaveDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new product",
                                                    description = "Complete request with all available fields for product",
                                                    value = "{\n" +
                                                            "  \"productName\": \"Choclate Milk\",\n" +
                                                            "  \"basicProductPrice\": 7,\n" +
                                                            "  \"productTypeId\": 1\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PostMapping
    public ResponseEntity saveProduct(@RequestBody ProProductSaveDto proProductSaveDto){
        ProProductDto proProductDtoSave = proProductService.saveProduct(proProductSaveDto);
        return ResponseEntity.ok(RestResponse.of(proProductDtoSave));
    }

    @Operation(
            tags = "Product Controller",
            description = "Update product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Products",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ProProductUpdateDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Update product",
                                                    description = "Complete request with all available fields for product",
                                                    value = "{\n" +
                                                            "  \"id\": 1,\n" +
                                                            "  \"productName\": \"Choclate Milk\",\n" +
                                                            "  \"basicProductPrice\": 5,\n" +
                                                            "  \"productTypeId\": 1\n" +
                                                            "}"
                                            )
                                    }
                            )
                    }
            )
    )
    @PutMapping
    public ResponseEntity updateProduct(@RequestBody ProProductUpdateDto proProductUpdateDto){
        ProProductDto proProductDtoUpdate = proProductService.updateProduct(proProductUpdateDto);
        return ResponseEntity.ok(RestResponse.of(proProductDtoUpdate));
    }

    @Operation(
            tags = "Product Controller",
            description = "Update price of product"
    )
    @PatchMapping
    public ResponseEntity updateProductPrice(@RequestParam Long id, @RequestParam BigDecimal price){
       ProProductDto proProductDto = proProductService.updateProductPrice(id, price);
       return ResponseEntity.ok(RestResponse.of(proProductDto));
    }

    @Operation(
            tags = "Product Controller",
            description = "Delete product"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        proProductService.deleteProduct(id);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
