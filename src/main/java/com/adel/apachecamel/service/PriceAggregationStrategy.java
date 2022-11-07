package com.adel.apachecamel.service;

import com.adel.apachecamel.model.Order;
import com.adel.apachecamel.model.OrderLine;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class PriceAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        final OrderLine newBody = newExchange.getIn().getBody(OrderLine.class);

        if(null == oldExchange){
            final Order order = new Order();
            order.setOrderNo(UUID.randomUUID().toString());
            order.setOrderDate(Instant.now().toString());
            order.setOrderPrice(newBody.getPrice());
            order.getOrderLines().add(newBody);
            newExchange.getIn().setBody(order, Order.class);
            return newExchange;
        }

        final OrderLine newOrderLine = newExchange.getIn().getBody(OrderLine.class);
        final Order order = oldExchange.getIn().getBody(Order.class);
        order.setOrderPrice(order.getOrderPrice() + newOrderLine.getPrice());
        order.getOrderLines().add(newOrderLine);
        oldExchange.getIn().setBody(order);

        return oldExchange;



    }
}
