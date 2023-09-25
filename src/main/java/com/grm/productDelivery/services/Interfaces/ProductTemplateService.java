package com.grm.productDelivery.services.Interfaces;

import com.grm.productDelivery.dto.ProductTemplateDto;
import com.grm.productDelivery.models.ProductTemplate;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ProductTemplateService {
    boolean create(ProductTemplateDto productTemplateDto) throws ParseException;

    boolean update(ProductTemplateDto productTemplateDto) throws ParseException;

    Optional<ProductTemplate> getProdTempById(String id);

    boolean deleteProdTempById(String id);

    List<ProductTemplate> getProductTempByFindAll();
}
