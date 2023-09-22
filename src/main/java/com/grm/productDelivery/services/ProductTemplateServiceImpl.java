package com.grm.productDelivery.services;

import com.grm.productDelivery.dao.ProductTemplateDao;
import com.grm.productDelivery.dto.ProductTemplateDto;
import com.grm.productDelivery.models.Product;
import com.grm.productDelivery.models.ProductTemplate;
import com.grm.productDelivery.services.Interfaces.ProductTemplateService;
import com.grm.productDelivery.util.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ProductTemplateServiceImpl implements ProductTemplateService {

    @Autowired
    private ProductTemplateDao productTemplateDao;

    @Override
    public boolean create(ProductTemplateDto productTemplateDto) throws ParseException{

        JSONObject source = new JSONObject(productTemplateDto);

        ProductTemplate pt = new ProductTemplate();
        log.info("Getting date");
        pt.setCreatedAt(DateHelper.getLocalDateTime());
        log.info("After Getting date");
        pt.setModifiedBy(DateHelper.getLocalDateTime());
        pt.setCreatedBy(productTemplateDto.getCreatedBy());
        pt.setRouteName(productTemplateDto.getRouteName());
        pt.setEntityId(productTemplateDto.getEntityId());
        pt.setEntityName(productTemplateDto.getEntityName());
        pt.setUserId(productTemplateDto.getUserId());

        List<Product> listP = new ArrayList<>();
        JSONArray jAProd = source.getJSONArray("products");
        for (int i = 0; i < jAProd.length(); i++) {
            Product p = new Product();
            JSONObject jsonObject = jAProd.getJSONObject(i);
            p.setId(DateHelper.generateUUID());
            p.setSkuName(jsonObject.getString("skuName"));
            p.setCategoryType(jsonObject.getString("categoryType"));
            p.setOriginalCost(jsonObject.getFloat("originalCost"));
            p.setSellingCost(jsonObject.getFloat("sellingCost"));
            listP.add(p);
        }
        pt.setProducts(listP);
        log.info("insert into mongo db from ProductTemplateServiceImpl Request ::");
        ProductTemplate spt = productTemplateDao.createProdTemp(pt);
        if (spt != null)
            return true;
        else
            return false;
    }
}
