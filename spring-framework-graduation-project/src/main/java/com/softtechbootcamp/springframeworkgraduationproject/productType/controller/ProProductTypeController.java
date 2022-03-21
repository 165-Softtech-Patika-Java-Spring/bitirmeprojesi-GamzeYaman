package com.softtechbootcamp.springframeworkgraduationproject.productType.controller;

import com.softtechbootcamp.springframeworkgraduationproject.general.dto.RestResponse;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductTypeSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import com.softtechbootcamp.springframeworkgraduationproject.productType.service.ProProductTypeService;
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

    @PostMapping
    public ResponseEntity saveProductType(@RequestBody ProProductTypeSaveDto proProductTypeSaveDto){
       ProProductTypeDto proProductTypeDtoSave = proProductTypeService.saveProductType(proProductTypeSaveDto);
       return ResponseEntity.ok(RestResponse.of(proProductTypeDtoSave));
    }


    @GetMapping("/product-type-details")
    public ResponseEntity findProductsByProductTypeId(){
        List<Object> proProductTypeDetails = proProductTypeService.getProductTypeDetails();
        return ResponseEntity.ok(RestResponse.of(proProductTypeDetails));
    }

    @PatchMapping
    public ResponseEntity updateKdvRateOfProductType(@RequestParam String productTypeName, @RequestParam BigDecimal kdvRate){
        ProProductTypeDto proProductTypeDto = proProductTypeService.updateKdvRateOfProductType(productTypeName, kdvRate);
        return ResponseEntity.ok(RestResponse.of(proProductTypeDto));
    }
}
