package com.coastroy.springrest.springrest.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {

  @RequestMapping(value = "/test/timeout", method = RequestMethod.GET)
  public ResponseEntity timeout(@RequestHeader("actionId") String actionId) {
    try {
      Thread.sleep(10 * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return ResponseEntity.ok("Ok");
  }
}
