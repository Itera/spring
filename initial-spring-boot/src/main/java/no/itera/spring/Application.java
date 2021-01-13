package no.itera.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class  Application implements CommandLineRunner {
  private final ApplicationContext context;

  public Application(ApplicationContext context) {
    this.context = context;
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) {
    CalculationSetterInjection calculationSetterInjection = context.getBean(CalculationSetterInjection.class);
    calculationSetterInjection.complexCalculation();

    CalculationConstructorInjection calculationConstructorInjection = context.getBean(CalculationConstructorInjection.class);
    calculationConstructorInjection.complexCalculation();

  }
}

