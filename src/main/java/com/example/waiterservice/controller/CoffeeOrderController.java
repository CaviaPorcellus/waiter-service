package com.example.waiterservice.controller;

import com.example.waiterservice.controller.request.OrderStateRequest;
import com.example.waiterservice.controller.request.OrderRequest;
import com.example.waiterservice.model.Coffee;
import com.example.waiterservice.model.CoffeeOrder;
import com.example.waiterservice.service.CoffeeOrderService;
import com.example.waiterservice.service.CoffeeService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/order")
public class CoffeeOrderController {

  @Autowired
  private CoffeeOrderService orderService;
  @Autowired
  private CoffeeService coffeeService;

  private RateLimiter rateLimiter;

  public CoffeeOrderController(RateLimiterRegistry registry) {
    this.rateLimiter = registry.rateLimiter("order");
  }

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @io.github.resilience4j.ratelimiter.annotation.RateLimiter(name = "order")
  public CoffeeOrder createOrder(@RequestBody @Valid OrderRequest orderRequest) {
    log.info("New order request: {}", orderRequest);
    Coffee[] coffees = coffeeService.getCoffeeByName(orderRequest.getCoffeeNames()).toArray(new Coffee[]{});

    return orderService.createOrder(orderRequest.getCustomer(), coffees);
  }

  @GetMapping(path = "/{id}")
  public CoffeeOrder getOrder(@PathVariable Long id) {
    CoffeeOrder order = null;
    try {
      order = rateLimiter.executeSupplier(() -> orderService.getOrder(id));
      log.info("Coffee order: {}", order);
    } catch (RequestNotPermitted e) {
      log.warn("Request not permitted ! {}", e.getMessage());
    }
    return order;
  }

  @PutMapping(path = "/{id}")
  public CoffeeOrder updateState(@PathVariable("id") Long id, @RequestBody OrderStateRequest orderState) {
    log.info("Update order id={} to state: {}", id, orderState);
    CoffeeOrder order = orderService.getOrder(id);
    orderService.updateOrder(order, orderState.getState());
    return order;
  }

  @ModelAttribute
  public List<Coffee> coffeeList() {
    return coffeeService.findAllCoffee();
  }
}
