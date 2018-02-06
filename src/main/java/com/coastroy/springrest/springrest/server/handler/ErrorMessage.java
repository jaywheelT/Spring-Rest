package com.coastroy.springrest.springrest.server.handler;

public class ErrorMessage {
  private String developerMessage;
  private String userMessage;

  public ErrorMessage(String developerMessage, String userMessage) {
    this.developerMessage = developerMessage;
    this.userMessage = userMessage;
  }
}
