package com.adel.apachecamel.controller;

import com.adel.apachecamel.model.Product;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductResourceController {

    private final ProducerTemplate producerTemplate;

    @Autowired
    public ProductResourceController(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
    }

    @GetMapping("/products/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") final String category){
        producerTemplate.start();
        final Product[] products = producerTemplate
                .requestBody("direct:fetchProducts", category, Product[].class);
        System.out.println("products "+products);
        producerTemplate.stop();
        return Arrays.asList(products);
    }
}
