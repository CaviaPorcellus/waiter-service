package com.example.waiterservice.controller;

import com.example.waiterservice.model.CoffeeRequest;
import com.example.waiterservice.model.Coffee;
import com.example.waiterservice.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping(path = "/coffee")
public class CoffeeController {

  @Autowired
  CoffeeService coffeeService;

  @GetMapping(path = "/", params = "!name")
  public List<Coffee> getAll() {
    return coffeeService.findAllCoffee();
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Coffee> getById(@PathVariable Long id) {
    Coffee c = coffeeService.getCoffee(id);
    log.info("Get by id: {}", c);
    return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
            .body(c);
  }

  /*
  @GetMapping(path = "/", params = "name")
  public Coffee getByName(@RequestParam String name) {
    return coffeeService.getCoffee(name);
  }
  */

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Coffee addJsonCoffee(@RequestBody @Valid CoffeeRequest request, BindingResult result) {
    if (result.hasErrors()) {
      log.error("Binding errors: {}", result);
      return null;
    }
    return coffeeService.addCoffee(request.getName(), request.getPrice());
  }

  /*
  @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Coffee addCoffee(@Valid CoffeeRequest request, BindingResult result) {
    if (result.hasErrors()) {
      log.error("Binding errors: {}", result);
//      throw new FormValidationException(result);
      return null;
    }
    return coffeeService.addCoffee(request.getName(), request.getPrice());
  }

  @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public List<Coffee> batchAddCoffee(@RequestParam("file") MultipartFile file) {

    List<Coffee> created = new ArrayList<>();

    if (!file.isEmpty()) {
      BufferedReader reader = null;
      try {
        reader = new BufferedReader(
            new InputStreamReader(file.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
          String[] params = line.split(",");
          if (params == null || params.length != 2) {
            continue;
          }

          Coffee coffee = coffeeService.addCoffee(params[0],
              Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(params[1])));
          created.add(coffee);
        }
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        IOUtils.closeQuietly(reader);
      }
    }
    return created;
  }
  */

}
