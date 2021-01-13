theme: Itera
slide-transition: true
autoscale: true

# Spring

![inline](logo.svg)

---

## Agenda

* What is Spring?
* IoC and DI (A run thru some small example applications)
* Common context issues
* More on Spring Beans
* @Configuration
* Spring MVC
* Databases
* Authentication
* Spring RestTemplate vs WebClient

--- 

# What is Spring?

> The Spring Framework is an application framework and inversion of control container for the Java platform
> -- Wikipedia [^1]

[^1]: https://en.wikipedia.org/wiki/Spring_Framework

---

# Yes - but what is Spring?

Core spring is based on the ideas of Inversion of Control (IoC) and Dependency Injection (DI) - so we'll start there.

---

## Dependency Injection (DI)

A class is provided with the services etc that it needs rather than creating them.

## Inversion of Control (IoC) - in Spring

IoC is a very open design principle - but in Spring terms it mostly refers to the spring container that provide the actual DI mechanics (creation of beans, injecting them following configuration etc).

---

# IoC and DI applications

---

# Initial code

We start with a simple application [^2]

[^2]: initial/pom.xml

---

## Services

* Calculator
* Display

---

## Business Logic

The calculation class performs a business operation using the services.

However - let's take a look at the code:

---

### Main method in the Business Logic

```java
  public void complexCalculation() {
    // Service 1
    Calculator calculator = new Calculator();

    int result = calculator.plus(2, 3);

    // Service 2
    Display display = new Display();

    display.output(String.format("2 + 3 = %d", result));  
  }
```

---

## Problems

* How do we test different implementations of either service?
* How do we even provide different implementations?

All of these require editing the business logic class.

---

# Dependency Injection

Let's take a look at how we can manually change this over to a DI based setup.

First round - manual DI - no spring [^3]

[^3]: initial-manual/pom.xml

---

## Constructor vs Setter

We can do this in two ways:

Provide (inject) the required services (dependencies) via:

* the constructor
* setters

---

## Setter injection

```java
  private Calculator calculator;
  private Display display;

  public void setCalculator(Calculator calculator) {
    this.calculator = calculator;
  }

  public void setDisplay(Display display) {
    this.display = display;
  }  
```

---

## Constructor injection

```java
  private final Calculator calculator;
  private final Display display;

  public CalculationConstructorInjection(Calculator calculator,
    Display display) {
    this.calculator = calculator;
    this.display = display;
  }
```

---

## Orchestration

OK - but how do we set up (or orchestrates) the application?

```java
  public static void main(String[] args) {
    // Services 
    Calculator calculator = new Calculator();
    Display display = new Display();

    // Setter injection
    CalculationSetterInjection calculationSetterInjection = new CalculationSetterInjection();
    calculationSetterInjection.setCalculator(calculator);
    calculationSetterInjection.setDisplay(display);

    calculationSetterInjection.complexCalculation();

    // Constructor injection
    CalculationConstructorInjection calculationConstructorInjection =
      new CalculationConstructorInjection(calculator, display);

    calculationConstructorInjection.complexCalculation();
  }
```

---

# Spring?

So far we have seen DI but had to orchestrate the application by hand.

Spring provides an IoC container - objects define what they need and the IoC container can then provide the required dependencies via DI.

We'll look at three ways:

* Old style (spring - with XML configured beans)
* Annotation style (spring - with annotated classes)
* Spring Boot

---

## Spring Beans

A spring bean is any object that is managed by the Spring IoC container.

A spring bean is usually a singleton (this is the default bean scope - we will look at scopes later on).

---

# Spring - XML

First steps are to grab some java libraries [^4]

We'll be using spring's context and beans.

[^4]: initial-spring/xml/pom.xml

---

## Application context

Spring provides a set of classes (based around BeanFactory) that allows us to configure the IoC container.

However - in nearly every project it is far far more common that it will use spring's application context for this.

---

## applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
    http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="display" class="no.itera.spring.Display"/>
  <bean id="calculator" class="no.itera.spring.Calculator"/>

  <bean id="calculationConstructorInjection" class="no.itera.spring.CalculationConstructorInjection">
    <constructor-arg name="calculator" ref="calculator"/>
    <constructor-arg name="display" ref="display"/>
  </bean>

  <bean id="calculationSetterInjection" class="no.itera.spring.CalculationSetterInjection">
    <property name="calculator" ref="calculator"/>
    <property name="display" ref="display"/>
  </bean>
</beans>
```

---

## Using the context

```java
    // Load the context
    ApplicationContext context =
      new ClassPathXmlApplicationContext("applicationContext.xml");

    // Get a bean by type
    CalculationSetterInjection calculationSetterInjection =
      context.getBean(CalculationSetterInjection.class);
    
    calculationSetterInjection.complexCalculation();

    // Get a bean by name
    CalculationConstructorInjection calculationConstructorInjection =
        (CalculationConstructorInjection) context.getBean("calculationConstructorInjection");
    calculationConstructorInjection.complexCalculation();
```

---

## Problems

This works - but - it means that the XML file is tightly coupled to the class structures.

If we change the java code we have to remember to adjust this file.

---

# Spring - Annotations

Let's modify the previous version using spring's component scanning mechanism (annotations) [^5]

[^5]: initial-spring/annotations/pom.xml

---

## Application context

The context file becomes a lot smaller - it simply configures what packages to scan

---

## applicationContext.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">

  <context:annotation-config/>
  <context:component-scan base-package="no.itera.spring"/>
</beans>
```

---

## Annotating classes

Classes get a class level annotation stating what sort of bean they are (@Service, @Component, @Repository etc)

Injection points are often marked @Autowired [^6]

[^6]: From Spring 4.3 a spring bean class with only one constructor does not need the autowired annotation - spring will wire it

---

## Using the context

The code in Application is exactly the same as for the XML version

---

## Notes

These examples are very simple. some other things we need to consider are

* bean scopes (is it a singleton? etc)
* qualifiers (requiring a bean and there are multiple implementations available)

## Problems

* Still a lot of boiler plate
* Managing dependencies in a larger project is still challenging
  
---

# Spring Boot

> Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
> We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need minimal Spring configuration.
-- Spring.io [^7]

[^7]: https://spring.io/projects/spring-boot/

---

Spring Boot tries to simplify:

* Setup
* Depdenency Management
* Configuration

---

## Spring Boot Starters

Spring Boot provides different starters - so that you can add support for different functionality.

We'll take a look at what's available after we've looked at the same test app in a Spring Boot version.[^8]

[^8]: initial-spring-boot/pom.xml

---

## Spring Boot Application

* Classes keep the same annotations as before
* Main class gets annotated `@SpringBootApplication`
* We will implement the CommandLineRunner as it is a command line app

---

```java
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
    CalculationSetterInjection calculationSetterInjection =
      context.getBean(CalculationSetterInjection.class);
    
    calculationSetterInjection.complexCalculation();

    CalculationConstructorInjection calculationConstructorInjection =
      context.getBean(CalculationConstructorInjection.class);
    
    calculationConstructorInjection.complexCalculation();
  }
}
```

---

## Spring Initializr

https://start.spring.io/

Under the Add Dependencies button you can see what starter packs you can add.

---

# Common context issues

---

TODO
---

# More on Spring Beans

---

TODO

---

# @Configuration

---

TODO

---

# Spring MVC

---

TODO

---

# Databases

---

TODO

---

# Authentication

---

TODO

---

# Spring RestTemplate vs WebClient

---

TODO

---
