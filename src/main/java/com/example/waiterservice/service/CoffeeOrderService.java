package com.example.waiterservice.service;

import com.example.waiterservice.model.Coffee;
import com.example.waiterservice.model.CoffeeOrder;
import com.example.waiterservice.model.OrderState;
import com.example.waiterservice.repository.CoffeeOrderRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Slf4j
@Service
@Transactional
public class CoffeeOrderService implements MeterBinder {

  @Autowired
  CoffeeOrderRepository orderRepository;

  private Counter counter;

  public CoffeeOrder createOrder(String customer, Coffee... coffee) {
    CoffeeOrder order = CoffeeOrder.builder()
        .customer(customer)
        .items(Arrays.asList(coffee))
        .state(OrderState.INIT)
        .build();
    CoffeeOrder saved = orderRepository.save(order);
    log.info("New order: {}", saved);
    this.counter.increment();
    return saved;
  }

  public boolean updateOrder(CoffeeOrder order, OrderState state) {
    if (state.compareTo(order.getState()) <= 0) {
      log.error("Wrong State order: {} -> {}", order.getState(), state);
      return false;
    }
    order.setState(state);
    CoffeeOrder saved = orderRepository.save(order);
    log.info("Saved order: {}", saved);
    return true;
  }

  public CoffeeOrder getOrder(Long id) {
    return orderRepository.getOne(id);
  }

  @Override
  public void bindTo(MeterRegistry registry) {
    this.counter = Counter.builder("order.count")
        .description("Order count")
        .tag("service", "Coffee order service")
        .register(registry);
  }
}
