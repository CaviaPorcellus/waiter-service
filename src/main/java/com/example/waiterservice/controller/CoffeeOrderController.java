package com.example.waiterservice.controller;

import com.example.waiterservice.controller.request.OrderRequest;
import com.example.waiterservice.model.Coffee;
import com.example.waiterservice.model.CoffeeOrder;
import com.example.waiterservice.service.CoffeeOrderService;
import com.example.waiterservice.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/order")
public class CoffeeOrderController {

  @Autowired
  CoffeeOrderService orderService;
  @Autowired
  CoffeeService coffeeService;

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public CoffeeOrder createOrder(@RequestBody @Valid OrderRequest orderRequest) {
    log.info("New order request: {}", orderRequest);
    Coffee[] coffees = coffeeService.getCoffeeByName(orderRequest.getCoffeeNames()).toArray(new Coffee[]{});

    return orderService.createOrder(orderRequest.getCustomer(), coffees);
  }

  @GetMapping(path = "/{id}")
  @ResponseBody
  public CoffeeOrder getOrder(@PathVariable Long id) {
    CoffeeOrder order = orderService.getOrder(id);
    log.info("Coffee order: {}", order);
    return order;
  }

  @ModelAttribute
  public List<Coffee> coffeeList() {
    return coffeeService.findAllCoffee();
  }
}
