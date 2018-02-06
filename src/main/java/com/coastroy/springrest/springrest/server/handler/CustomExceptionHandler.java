package com.coastroy.springrest.springrest.server.handler;

import com.google.gson.Gson;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<String> handleException(final NullPointerException e) {
    ErrorMessage errorMessage = new ErrorMessage("program bad", "cannot found...");
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(new Gson().toJson(errorMessage), httpHeaders, HttpStatus.BAD_REQUEST);
  }
}
