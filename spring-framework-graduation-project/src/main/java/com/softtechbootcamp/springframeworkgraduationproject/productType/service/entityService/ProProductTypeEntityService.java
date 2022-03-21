package com.softtechbootcamp.springframeworkgraduationproject.productType.service.entityService;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.GeneralErrorMessage;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.DuplicateException;
import com.softtechbootcamp.springframeworkgraduationproject.general.service.BaseEntityService;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dao.ProProductTypeDao;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProProductTypeEntityService extends BaseEntityService<ProProductType, ProProductTypeDao> {
    private final ProProductTypeDao proProductTypeDao;

    public ProProductTypeEntityService(ProProductTypeDao dao, ProProductTypeDao proProductTypeDao) {
        super(dao);
        this.proProductTypeDao = proProductTypeDao;
    }

    public List<Object> getProductTypeDetails(){
        return proProductTypeDao.getProductTypeDetails();
    }
    public ProProductType update(String productTypeName, BigDecimal taxRate){
        ProProductType proProductType = getNameWithControl(productTypeName);
        proProductType.setTaxRate(taxRate);
        return proProductTypeDao.save(proProductType);
    }

    public ProProductType getNameWithControl(String productTypeName) {
        Optional<ProProductType> proProductTypeOptional = proProductTypeDao.findByProductTypeName(productTypeName);

        ProProductType proProductType;
        if(proProductTypeOptional.isPresent()){
            proProductType = proProductTypeOptional.get();
        }else{
            throw new DuplicateException(GeneralErrorMessage.PRODUCT_TYPE_NOT_FOUND);
        }

        return proProductType;
    }

    public Boolean isProductTypeExist(String name){
        return proProductTypeDao.existsByProductTypeName(name);
    }
}
