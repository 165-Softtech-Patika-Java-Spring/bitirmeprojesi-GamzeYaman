package com.softtechbootcamp.springframeworkgraduationproject.product.controller;

import com.softtechbootcamp.springframeworkgraduationproject.general.dto.RestResponse;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.dto.ProProductSaveDto;
import com.softtechbootcamp.springframeworkgraduationproject.product.service.ProProductService;
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

    @GetMapping
    public ResponseEntity findAllProducts(){
        List<ProProductDto> proProductDtoList = proProductService.findAllProducts();
        return ResponseEntity.ok(RestResponse.of(proProductDtoList));
    }

    @GetMapping("/product-type-id/{productTypeId}")
    public ResponseEntity findProductsByProductTypeId(@PathVariable Long productTypeId){
        List<ProProductDto> proProductDtoList = proProductService.findByProductTypeId(productTypeId);
        return ResponseEntity.ok(RestResponse.of(proProductDtoList));
    }

    @GetMapping("/between-prices")
    public ResponseEntity getProductPrice(@RequestParam BigDecimal firstPrice, @RequestParam BigDecimal secondPrice){
       List<ProProductDto> proProductDto = proProductService.findAllProducts(firstPrice, secondPrice);
        return ResponseEntity.ok(RestResponse.of(proProductDto));
    }

    @PostMapping
    public ResponseEntity saveProduct(@RequestBody ProProductSaveDto proProductSaveDto){
        ProProductDto proProductDtoSave = proProductService.saveProduct(proProductSaveDto);
        return ResponseEntity.ok(RestResponse.of(proProductDtoSave));
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody ProProductDto proProductDto){
        ProProductDto proProductDtoUpdate = proProductService.updateProduct(proProductDto);
        return ResponseEntity.ok(RestResponse.of(proProductDtoUpdate));
    }

    @PatchMapping
    public ResponseEntity updateProductPrice(@RequestParam Long id, @RequestParam BigDecimal price){
       ProProductDto proProductDto = proProductService.updateProductPrice(id, price);
       return ResponseEntity.ok(RestResponse.of(proProductDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        proProductService.deleteProduct(id);
        return ResponseEntity.ok(RestResponse.empty());
    }
}
