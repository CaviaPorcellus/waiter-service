package com.example.waiterservice.controller.request;

import com.example.waiterservice.model.OrderState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStateRequest {

  private OrderState state;

}
