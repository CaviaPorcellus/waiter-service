package com.example.waiterservice.integration;

import com.example.waiterservice.model.CoffeeOrder;
import com.example.waiterservice.service.CoffeeOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
@Transactional
public class OrderListener {

  @Autowired
  private CoffeeOrderService orderService;
  @Autowired
  private Customer customer;

  @StreamListener(Barista.FINISHED_ORDERS)
  @SendTo(Customer.NOTIFY_ORDERS)
  public Long listenOrderFinished(Long id) {
    log.info("We've finished order id={}", id);
    CoffeeOrder order = orderService.getOrder(id);
    log.info("Notify the customer: {}", order.getCustomer());
    return id;
  }

}
