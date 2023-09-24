package com.grm.productDelivery.services;

import com.grm.productDelivery.dao.ProductTemplateDao;
import com.grm.productDelivery.dto.ProductTemplateDto;
import com.grm.productDelivery.models.Product;
import com.grm.productDelivery.models.ProductTemplate;
import com.grm.productDelivery.repositories.ProductTemplateRepository;
import com.grm.productDelivery.services.Interfaces.ProductTemplateService;
import com.grm.productDelivery.util.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductTemplateServiceImpl implements ProductTemplateService {

    @Autowired
    private ProductTemplateDao productTemplateDao;

    @Autowired
    private ProductTemplateRepository templateRepository;

    @Override
    public boolean create(ProductTemplateDto productTemplateDto) throws ParseException {

        JSONObject source = new JSONObject(productTemplateDto);

        ProductTemplate pt = new ProductTemplate();
        pt.setCreatedAt(DateHelper.getLocalDateTime());
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
            p.setStatus(jsonObject.getBoolean("status"));
            listP.add(p);
        }
        pt.setProducts(listP);
        log.info("insert into mongo db from ProductTemplateServiceImpl Request ::");
        ProductTemplate spt = productTemplateDao.createProdTemp(pt);
        return (spt != null);
    }

    public boolean update(ProductTemplateDto productTemplateDto) throws ParseException {

        JSONObject source = new JSONObject(productTemplateDto);

        ProductTemplate pt = new ProductTemplate();
        pt.setId(productTemplateDto.getId());
        pt.setCreatedAt(DateHelper.getLocalDateTime());
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
            p.setStatus(jsonObject.getBoolean("status"));
            listP.add(p);
        }
        pt.setProducts(listP);
        Optional<ProductTemplate> ptData = getProdTempById(productTemplateDto.getId());
        if (ptData.isPresent()) {
            log.info("updating product template into mongo db from ProductTemplateServiceImpl Request ::");
            templateRepository.save(pt);
            return true;
        } else
            return false;

    }

    @Override
    public Optional<ProductTemplate> getProdTempById(String id) {
        return templateRepository.findById(id);
    }

    @Override
    public boolean deleteProdTempById(String id) {
        Optional<ProductTemplate> ptData = getProdTempById(id);
        if (ptData.isPresent()) {
            log.info("Deleting product template record from  mongo db in ProductTemplateServiceImpl Request ::");
            templateRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    public List<ProductTemplate> getProductTempByFindAll() {
        return templateRepository.findAll();
    }
}
