package com.grm.productDelivery.services.Interfaces;

import com.grm.productDelivery.dto.ProductTemplateDto;

import java.text.ParseException;

public interface ProductTemplateService {
    boolean create(ProductTemplateDto productTemplateDto) throws ParseException;
}
