package com.itera.springbootdbexample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "demo_child")
public class Child {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  String name;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "parent", nullable = false)
  private Parent parent;

  public Child() {
  }

  public Child(String name, Parent parent) {
    this.name = name;
    this.parent = parent;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Parent getParent() {
    return parent;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setParent(Parent parent) {
    this.parent = parent;
  }
}
