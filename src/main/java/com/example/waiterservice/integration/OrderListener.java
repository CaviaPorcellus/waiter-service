package com.example.waiterservice.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderListener {

  @StreamListener(Barista.FINISHED_ORDERS)
  public void listenOrderFinished(Long id) {
    log.info("We've finished order id={}", id);
  }

}
