package com.softtechbootcamp.springframeworkgraduationproject.productType.dao;

import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductDetailsDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProProductTypeDao extends JpaRepository<ProProductType, Long> {

    Optional<ProProductType> findByProductTypeName(String name);
    Boolean existsByProductTypeName(String name);

    @Query(
            value = "select new com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductDetailsDto(proProductType.productTypeName, proProductType.taxRate, min(proProduct.productPriceWithTax), max(proProduct.productPriceWithTax), count(proProduct.id)) " +
                    " from ProProduct proProduct " +
                    " left join ProProductType proProductType on proProductType.id = proProduct.productTypeId " +
                    " group by proProductType.productTypeName, proProductType.taxRate"
    )
    List<ProProductDetailsDto> getProductDetails();
}
