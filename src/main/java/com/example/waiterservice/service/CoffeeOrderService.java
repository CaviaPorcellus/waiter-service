package com.example.waiterservice.service;

import com.example.waiterservice.integration.Barista;
import com.example.waiterservice.model.Coffee;
import com.example.waiterservice.model.CoffeeOrder;
import com.example.waiterservice.model.OrderState;
import com.example.waiterservice.repository.CoffeeOrderRepository;
import com.example.waiterservice.support.OrderProperties;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@Transactional
public class CoffeeOrderService implements MeterBinder {

  @Autowired
  CoffeeOrderRepository orderRepository;
  @Autowired
  OrderProperties orderProperties;
  @Autowired
  Barista barista;

  private String waiterId = UUID.randomUUID().toString();

  private Counter counter;

  public CoffeeOrder createOrder(String customer, Coffee... coffee) {
    CoffeeOrder order = CoffeeOrder.builder()
        .customer(customer)
        .items(Arrays.asList(coffee))
        .state(OrderState.INIT)
        .discount(orderProperties.getDiscount())
        .total(calcTotal(coffee))
        .waiter(orderProperties.getPrefix() + waiterId)
        .build();
    CoffeeOrder saved = orderRepository.save(order);
    log.info("New order: {}", saved);
    this.counter.increment();
    return saved;
  }

  public boolean updateOrder(CoffeeOrder order, OrderState state) {
    if (order == null) {
      log.warn("Cannot find order.");
      return false;
    }
    if (state.compareTo(order.getState()) <= 0) {
      log.error("Wrong State order: {} -> {}", order.getState(), state);
      return false;
    }
    order.setState(state);
    orderRepository.save(order);
    log.info("Updated order: {}", order);
    if (state == OrderState.PAID) {
      // send messages
      barista.newOrders().send(MessageBuilder.withPayload(order.getId()).build());
    }
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

  private Money calcTotal(Coffee...coffee) {
    List<Money> items = Stream.of(coffee).map(c -> c.getPrice())
        .collect(Collectors.toList());
    return Money.total(items).multipliedBy(orderProperties.getDiscount())
        .dividedBy(100, RoundingMode.HALF_UP);
  }
}
