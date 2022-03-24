package com.softtechbootcamp.springframeworkgraduationproject.productType.controller;

import com.softtechbootcamp.springframeworkgraduationproject.general.dto.RestResponse;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductDetailsDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.service.ProProductTypeService;
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
@RequestMapping("/product-types")
@RequiredArgsConstructor
public class ProProductTypeController {
    private final ProProductTypeService proProductTypeService;

    @Operation(
            tags = "Product Type Controller",
            description = "Creates new product type",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Product Types",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = ProProductTypeSaveDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    name = "new product type",
                                                    description = "Complete request with all available fields for product type",
                                                    value = "{\n" +
                                                            "  \"productTypeName\": \"FOOD\",\n" +
                                                            "  \"taxRate\": 1\n" +
                                                            "}"
                                            ),
                                    }
                            ),
                    }
            )
    )
    @PostMapping
    public ResponseEntity saveProductType(@RequestBody ProProductTypeSaveDto proProductTypeSaveDto){
       ProProductTypeDto proProductTypeDtoSave = proProductTypeService.saveProductType(proProductTypeSaveDto);
       return ResponseEntity.ok(RestResponse.of(proProductTypeDtoSave));
    }


    @Operation(
            tags = "Product Type Controller",
            description = "Update tax rate of product type by using type name"
    )
    @PatchMapping
    public ResponseEntity updateTaxRateOfProductType(@RequestParam String productTypeName, @RequestParam BigDecimal taxRate){
        ProProductTypeDto proProductTypeDto = proProductTypeService.updateTaxRateOfProductType(productTypeName, taxRate);
        return ResponseEntity.ok(RestResponse.of(proProductTypeDto));
    }

    @Operation(
            tags = "Product Type Controller",
            description = "List some summarized informations about product for each productType "
    )
    @GetMapping("/details")
    public ResponseEntity getProductDetails(){
        List<ProProductDetailsDto> proProductDetailsDto = proProductTypeService.getProductTypeDetails();
        return ResponseEntity.ok(RestResponse.of(proProductDetailsDto));
    }
}
