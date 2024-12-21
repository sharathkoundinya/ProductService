package org.redlotus.productservice.configs;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
/*
* This is the RestTemplateConfig which will be used by all the project
*
* Notice that RestTemplateBuilder() is built-in. Know more about this
*
*
*/
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate getNewRestTemplate() {
        return new RestTemplateBuilder().build();
    }

}
