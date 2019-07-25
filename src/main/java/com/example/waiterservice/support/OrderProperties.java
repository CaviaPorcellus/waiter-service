package com.example.waiterservice.support;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@ConfigurationProperties("order")
@RefreshScope
@Data
@Component
public class OrderProperties {
  private int discount = 100;
  private String prefix = "Springbucks";
}
