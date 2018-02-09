package com.coastroy.springrest.springrest.server;

import com.coastroy.springrest.springrest.models.Aqua;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AquaController {
  @RequestMapping(value = "/aquas", method = RequestMethod.POST)
  public Aqua createAqua(@RequestBody Aqua aqua) {
    return aqua;
  }
}
