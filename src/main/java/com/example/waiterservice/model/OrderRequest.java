package com.example.waiterservice.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class OrderRequest {

  @Size(min = 1)
  private String customer;

  @NotNull
  private List<String> coffeeNames;
}
