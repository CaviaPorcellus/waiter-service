package com.example.waiterservice.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormValidationException extends RuntimeException {

  private BindingResult result;

  public FormValidationException(BindingResult result) {
    this.result = result;
  }

  public BindingResult getResult() {
    return result;
  }
}
