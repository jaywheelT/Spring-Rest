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
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
    restTemplate.setInterceptors(Collections.singletonList(actionTrackInterceptor));
    return restTemplate;
  }
}
