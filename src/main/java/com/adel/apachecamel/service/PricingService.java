package com.adel.apachecamel.service;

import com.adel.apachecamel.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public OrderLine calculatePrice(final OrderLine orderLine){
        final String category = orderLine.getProduct().getProductCategory();
        if(category.equalsIgnoreCase("Electronics")){
            orderLine.setPrice(300.0);
        }else if(category.equalsIgnoreCase("Household")){
            orderLine.setPrice(55.0);
        }else {
            orderLine.setPrice(0.0);
        }
        return orderLine;
    }

}
