package com.example.waiterservice.service;

import com.example.waiterservice.model.Coffee;
import com.example.waiterservice.repository.CoffeeRepository;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "coffee")
public class CoffeeService {

  @Autowired
  CoffeeRepository coffeeRepository;

//  @Cacheable
  public List<Coffee> findAllCoffee() {
    return coffeeRepository.findAll(Sort.by("id"));
  }

  public List<Coffee> getCoffeeByName(List<String> names) {
    return coffeeRepository.findByNameInOrderById(names);
  }

  public Coffee getCoffee(Long id) {
    return coffeeRepository.getOne(id);
  }

  public Coffee getCoffee(String name) {
    return coffeeRepository.findByName(name);
  }

//  @CacheEvict
  public Coffee addCoffee(String name, Money price) {
    return coffeeRepository.save(Coffee.builder().name(name).price(price).build());
  }

  public long getCoffeeCount() {
    return coffeeRepository.count();
  }

}
