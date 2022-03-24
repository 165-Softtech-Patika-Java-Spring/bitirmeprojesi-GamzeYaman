package com.softtechbootcamp.springframeworkgraduationproject.product.dao;

import com.softtechbootcamp.springframeworkgraduationproject.product.entity.ProProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProProductDao extends JpaRepository<ProProduct, Long> {

 List<ProProduct> findByProductTypeId(Long id);
 List<ProProduct> findAllByProductPriceWithTaxBetween(BigDecimal firstPrice, BigDecimal secondPrice);
}
