package com.example.waiterservice.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.Money;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class CoffeeRequest {

  @NotEmpty
  private String name;

  @NotNull
  private Money price;
}
