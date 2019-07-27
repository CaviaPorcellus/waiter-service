package com.example.waiterservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder extends BaseEntity {

  private String customer;

  @ManyToMany
  @JoinTable(name = "T_ORDER_COFFEE")
  @OrderBy("id")
  private List<Coffee> items;

  @Enumerated
  @Column(nullable = false)
  private OrderState state;

  private Integer discount;

  @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyMinorAmount",
      parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
  private Money total;

  private String waiter;

  private String barista;

}
