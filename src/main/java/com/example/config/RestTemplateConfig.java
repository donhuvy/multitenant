package com.example.config;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Prototype scoped RestTemplate so it X-TenantID Header can be passed to the RESTful API call.
 */
@Configuration
public class RestTemplateConfig {

    public static MultiValueMap<String, String> getHeaders() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        Map map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        headers.setAll(map);
        return headers;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<ClientHttpRequestInterceptor> clientHttpRequestInterceptorList = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(clientHttpRequestInterceptorList)) {
            clientHttpRequestInterceptorList = new ArrayList<>();
        }
        clientHttpRequestInterceptorList.add(new RestTemplateHeaderModifierInterceptor());
        restTemplate.setInterceptors(clientHttpRequestInterceptorList);
        return restTemplate;
    }

}
