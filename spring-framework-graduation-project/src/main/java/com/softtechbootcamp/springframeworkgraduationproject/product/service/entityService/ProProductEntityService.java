package com.softtechbootcamp.springframeworkgraduationproject.product.service.entityService;

import com.softtechbootcamp.springframeworkgraduationproject.general.service.BaseEntityService;
import com.softtechbootcamp.springframeworkgraduationproject.product.dao.ProProductDao;
import com.softtechbootcamp.springframeworkgraduationproject.product.entity.ProProduct;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ProProductEntityService extends BaseEntityService<ProProduct, ProProductDao> {
    private ProProductDao proProductDao;

    public ProProductEntityService(ProProductDao dao, ProProductDao proProductDao) {
        super(dao);
        this.proProductDao = proProductDao;
    }

    public List<ProProduct> findByProductTypeId(Long id){
        return proProductDao.findByProductTypeId(id);
    }

    public List<ProProduct> findAllByProductBetweenTwoPrices(BigDecimal firstPrice, BigDecimal secondPrice){
        return proProductDao.findAllByProductPriceWithTaxBetween(firstPrice, secondPrice);
    }

}
