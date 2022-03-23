package com.softtechbootcamp.springframeworkgraduationproject.productType.dao;

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
            "select proProductType.productTypeName as ProductType, proProductType.taxRate as KdvRate, min(proProduct.productPriceWithTax) as MinProductPrice, max(proProduct.productPriceWithTax) as MaxProductPrice, avg(proProduct.productPriceWithTax) as AvgProductPrices, count(proProduct.id) as NumberOfProducts " +
                    " from ProProduct proProduct " +
                    " left join ProProductType proProductType on proProductType.id = proProduct.productTypeId " +
                    " group by proProductType.productTypeName, proProductType.taxRate"
    )
    List<Object> getProductTypeDetails();




}
