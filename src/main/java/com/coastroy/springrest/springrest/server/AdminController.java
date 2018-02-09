package com.coastroy.springrest.springrest.server;

import com.coastroy.springrest.springrest.server.service.BeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin/v1")
public class AdminController {
  @Autowired
  BeachService beachService;

  @RequestMapping("/actions")
  public Map<String, String> actions() {
    return beachService.getActionMap();
  }
}
