package com.coastroy.springrest.springrest.client;

import com.coastroy.springrest.springrest.models.Beach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {
  public static final String SERVER_URL = "http://localhost:8081/api/v1/";
  @Autowired
  RestTemplate restTemplate;

  @RequestMapping("/timeout")
  public String timeout() {
    String response = null;
    try {
      response = restTemplate.getForObject(SERVER_URL + "test/timeout", String.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  @RequestMapping("/beaches")
  public List<Beach> getBeaches() {
    return restTemplate.getForObject(SERVER_URL + "beaches", List.class);
  }

  @RequestMapping("/beaches/operation")
  public Map<String, Beach> operationBeach(@RequestParam int id,
                                           @RequestParam(required = false) String name,
                                           @RequestParam(required = false) Integer capacity,
                                           @RequestParam String operation) {
    Map<String, Beach> response = new HashMap<>();
    if (operation.equals("update")) {
      Beach beach = new Beach(id, name, capacity);
      HttpEntity<Beach> requestEntity = new HttpEntity<>(beach);
      ResponseEntity<Beach> responseEntity = restTemplate.exchange(SERVER_URL + "beaches/" + String.valueOf(id),
          HttpMethod.PUT, requestEntity, Beach.class);
      response.put(responseEntity.getStatusCode().toString(), responseEntity.getBody());
    }
    if (operation.equals("updatep")) {
      Beach beach = new Beach(id, name, capacity);
      HttpEntity<Beach> requestEntity = new HttpEntity<>(beach);
      ResponseEntity<Beach> responseEntity = restTemplate.exchange(SERVER_URL + "beaches/" + String.valueOf(id),
          HttpMethod.PATCH, requestEntity, Beach.class);
      response.put(responseEntity.getStatusCode().toString(), responseEntity.getBody());
    }
    if (operation.equals("add")) {
      Beach beach = new Beach(id, name, capacity);
      HttpEntity<Beach> requestEntity = new HttpEntity<>(beach);
      ResponseEntity<Beach> responseEntity = restTemplate.exchange(SERVER_URL + "beaches",
          HttpMethod.POST, requestEntity, Beach.class);
      response.put(responseEntity.getStatusCode().toString(), responseEntity.getBody());
    }
    if (operation.equals("delete")) {
      HttpEntity<Beach> requestEntity = new HttpEntity<>(new HttpHeaders());
      ResponseEntity<Beach> responseEntity = restTemplate.exchange(SERVER_URL + "beaches/" + String.valueOf(id),
          HttpMethod.DELETE, requestEntity, Beach.class);
      response.put(responseEntity.getStatusCode().toString(), null);
    }
    return response;
  }
}