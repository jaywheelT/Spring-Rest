package com.coastroy.springrest.springrest.client;

import com.coastroy.springrest.springrest.models.Beach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/client")
public class RequestController {
  public static final String SERVER_URL = "http://localhost:8081/server/";
  @Autowired
  RestTemplate restTemplate;

  @RequestMapping("/requestHeader")
  public Beach requestHeader() {
    return restTemplate.getForObject(SERVER_URL + "beaches", Beach.class);
  }

  @RequestMapping("/deleteResource")
  public String deleteResource() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(SERVER_URL + "beaches", HttpMethod.DELETE, requestEntity,
        String.class);
    return responseEntity.getStatusCode().toString();
  }
}
