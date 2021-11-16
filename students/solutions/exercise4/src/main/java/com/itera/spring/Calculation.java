package com.itera.spring;

import org.springframework.stereotype.Component;

@Component
public class Calculation {
  private final Calculator calculator;
  private final Display display;

  public Calculation(Calculator calculator,
                     Display display) {
    this.calculator = calculator;
    this.display = display;
  }

  public void complexCalculation() {
    int result = calculator.plus(2, 3);

    display.output(String.format("2 + 3 = %d", result));
  }
}
