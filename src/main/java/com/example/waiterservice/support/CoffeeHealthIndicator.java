package com.example.waiterservice.support;

import com.example.waiterservice.service.CoffeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class CoffeeHealthIndicator extends AbstractHealthIndicator {

  @Autowired
  CoffeeService coffeeService;

  @Override
  protected void doHealthCheck(Health.Builder builder) throws Exception {
    long count = coffeeService.getCoffeeCount();

    if (count > 0) {
      builder.up()
          .withDetail("count", count)
          .withDetail("message", "We have enough coffee");
    } else {
      builder.down()
          .withDetail("count", count)
          .withDetail("message", "We have enough coffee");
    }
  }
}
