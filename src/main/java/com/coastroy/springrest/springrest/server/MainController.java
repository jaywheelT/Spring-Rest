package com.coastroy.springrest.springrest.server;

import com.coastroy.springrest.springrest.models.Beach;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class MainController {
  @RequestMapping("/beaches")
  public Beach beaches(@RequestHeader("operationId") String operationId) {
    System.out.println(operationId);
    return new Beach(1, "Beach Coast", 300);
  }

  @RequestMapping(value = "/beaches", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void beaches() {
  }
}
