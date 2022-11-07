package com.adel.apachecamel.routes;

import com.adel.apachecamel.service.PriceAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingRoute extends RouteBuilder {

    private final PriceAggregationStrategy priceAggregationStrategy;

    @Autowired
    public OrderProcessingRoute(PriceAggregationStrategy priceAggregationStrategy) {
        this.priceAggregationStrategy = priceAggregationStrategy;
    }

    @Override
    public void configure() throws Exception {
        from("direct:fetchProcess")
                .split(body(), priceAggregationStrategy).parallelProcessing()
                .to("bean:pricingService?method=calculatePrice")
                .end();
    }
}
