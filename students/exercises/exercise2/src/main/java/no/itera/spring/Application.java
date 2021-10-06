package no.itera.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
  public static void main(String[] args) {
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    Calculation calculation = context.getBean(Calculation.class);
    calculation.complexCalculation();
  }
}
