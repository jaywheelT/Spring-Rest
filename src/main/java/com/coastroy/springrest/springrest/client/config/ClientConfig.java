package com.coastroy.springrest.springrest.client.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
public class ClientConfig {
  @Autowired
  ActionTrackInterceptor actionTrackInterceptor;

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    HttpClient httpClient = HttpClients.createDefault();
    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
    //requestFactory.setConnectionRequestTimeout(5 * 1000);
    //requestFactory.setConnectTimeout(2 * 1000);
    requestFactory.setReadTimeout(2 * 1000);
    restTemplate.setRequestFactory(requestFactory);
    restTemplate.setInterceptors(Collections.singletonList(actionTrackInterceptor));
    return restTemplate;
  }
}
