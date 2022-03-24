package com.softtechbootcamp.springframeworkgraduationproject.productType.service.entityService;

import com.softtechbootcamp.springframeworkgraduationproject.general.exceptionEnums.GeneralErrorMessage;
import com.softtechbootcamp.springframeworkgraduationproject.general.exceptions.ItemNotFoundExceptions;
import com.softtechbootcamp.springframeworkgraduationproject.general.service.BaseEntityService;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dao.ProProductTypeDao;
import com.softtechbootcamp.springframeworkgraduationproject.productType.dto.ProProductDetailsDto;
import com.softtechbootcamp.springframeworkgraduationproject.productType.entity.ProProductType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProProductTypeEntityService extends BaseEntityService<ProProductType, ProProductTypeDao> {
    private final ProProductTypeDao proProductTypeDao;

    public ProProductTypeEntityService(ProProductTypeDao dao, ProProductTypeDao proProductTypeDao) {
        super(dao);
        this.proProductTypeDao = proProductTypeDao;
    }

    public ProProductType update(String productTypeName, BigDecimal taxRate){
        ProProductType proProductType = getNameWithControl(productTypeName);
        proProductType.setTaxRate(taxRate);
        return proProductTypeDao.save(proProductType);
    }

    public List<ProProductDetailsDto> getProductTypeDetails(){
        return proProductTypeDao.getProductDetails();
    }

    /* Product type name is unique, so searching can be also made by product type name. Name was converted to uppercase because of saving style of product type name to database. */
    public ProProductType getNameWithControl(String productTypeName) {
        productTypeName = productTypeName.toUpperCase(Locale.ROOT);
        Optional<ProProductType> proProductTypeOptional = proProductTypeDao.findByProductTypeName(productTypeName);

        ProProductType proProductType;
        if(proProductTypeOptional.isPresent()){
            proProductType = proProductTypeOptional.get();
        }else{
            throw new ItemNotFoundExceptions(GeneralErrorMessage.PRODUCT_TYPE_NOT_FOUND);
        }

        return proProductType;
    }

    public Boolean isProductTypeNameExist(String name){
        return proProductTypeDao.existsByProductTypeName(name);
    }
}
