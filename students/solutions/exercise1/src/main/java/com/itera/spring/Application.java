package com.itera.spring;

public class Application {
  public static void main(String[] args) {
    Calculator calculator = new Calculator();
    Display display = new Display();

    Calculation calculation = new Calculation(calculator, display);

    calculation.complexCalculation();
  }
}
