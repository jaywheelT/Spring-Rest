package com.coastroy.springrest.springrest.client;

import com.coastroy.springrest.springrest.models.Aqua;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/client")
public class AquaClientController {
  public static final String SERVER_URL = "http://localhost:8081/api/v1/";

  @Autowired
  RestTemplate restTemplate;

  @RequestMapping("/aqua")
  public Aqua aqua() {
    Aqua aqua = new Aqua("1");
    aqua.setDrops(Arrays.asList("drop1", "drop2"));
    return restTemplate.postForObject(SERVER_URL + "aquas", aqua, Aqua.class);
  }
}
