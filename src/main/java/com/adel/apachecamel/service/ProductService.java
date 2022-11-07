package com.adel.apachecamel.service;

import com.adel.apachecamel.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public List<Product> fetchProductByCategory(final String category){
        System.out.println("fetchProductByCategory "+ category);

        final List<Product> products = new ArrayList<>();
        products.add(new Product("Television","Electronics"));
        products.add(new Product("Washing Machine","Household"));
        return products;
    }

}
