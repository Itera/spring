# Exercise 5

## Set up project

* Head to https://start.spring.io/

* Set up a project:
  * maven based
  * java (or if you wish to try it out - kotlin)
  * Group: no.itera
  * Artifact: spring-boot-web-example
  * Name: spring-boot-web-example
  * Package name: no.itera.spring
  * Packaging: jar
  * Java version: 11

* Add Spring Web as dependency

* Download

## Exercise

### Model Layer

Create a package no.itera.spring.models

Create a model class in that package - a java bean with two properties:

* id: Integer
* name: String

### Service Layer

Create a package no.itera.spring.services

Create a service (@Service) that has methods for:

* Get - all
* Get - by ID
* Post - create
* Delete - delete

For a data store - use a list as instance variable - just to keep it simple.

### Resource Layer

Create a package no.itera.spring.resources

Create a web resource class (@RestController) that has methods for:

* Get on path / - return all items from service - use @ResponseBody

* Get on path /{id} - takes on id as a path variable - returns the single item requested - or throw a ResponseStatusException of type NOT_FOUND if not found - use @ResponseBody

* Post on path / - add a new item - if an item with the same id exists - throw a ResponseStatusException of type CONFLICT

* Delete on path / - takes in id as a query parameter - deletes the item if found - NOT_FOUND if not.

