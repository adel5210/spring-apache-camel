package com.adel.apachecamel.config;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.MXBean;

@Configuration
public class CamelConfig {

    private final CamelContext camelContext;

    @Autowired
    public CamelConfig(CamelContext camelContext) {
        this.camelContext = camelContext;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        final String contextPath = "/ecommapp";
        final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new CamelHttpTransportServlet(), contextPath+"/*"
        );
        servletRegistrationBean.setName("CamelServlet");
        return servletRegistrationBean;
    }

    @Bean
    public ProducerTemplate producerTemplate(){
        return camelContext.createProducerTemplate();
    }

    @Bean
    public ConsumerTemplate consumerTemplate(){
        return camelContext.createConsumerTemplate();
    }
}
