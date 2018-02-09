package com.coastroy.springrest.springrest.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Aqua {
  private List<String> drops;
  private String id;

  /**
   * No default constructor(without parameter)
   * Use @JsonCreator to tell Jackson how to create
   */
  @JsonCreator
  public Aqua(@JsonProperty("id") String id) {
    this.id = id;
  }

  public List<String> getDrops() {
    return drops;
  }

  public void setDrops(List<String> drops) {
    this.drops = drops;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
