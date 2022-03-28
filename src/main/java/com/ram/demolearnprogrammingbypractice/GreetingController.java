package com.ram.demolearnprogrammingbypractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/my-service")
public class GreetingController {

  @GetMapping("/hello")
  public String saySomething() {
    return "Hello Ram!";
  }
}
