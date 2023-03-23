# Exercise 4

## Set up project

* Head to https://start.spring.io/

* Set up a project:
  * maven based
  * java
  * Group: com.itera
  * Artifact: spring-boot
  * Name: spring-boot
  * Package name: com.itera.spring
  * Packaging: jar
  * Java version: 19

* No dependencies needed

* Download

## Business Logic

This uses the same code as the previous exercise. Copy the two service classes and the calculation class to the project.

## Exercise

### Application Context

We need access to the application context.

Create a constructor in Application that takes in ApplicationContext and store it as an instance variable on the class. You do not need to mark the constructor as Autowired - we only have one constructor so spring will automatically inject to that.

### Command Line Runner

This application needs to be a command line application. Application should Implement CommandLineRunner.

### Complete 

In the run method - we need to get our Calculation bean from the context and then call complexCalculation on it. Check the previous exercise if you need a hint on how to get a bean from the context.