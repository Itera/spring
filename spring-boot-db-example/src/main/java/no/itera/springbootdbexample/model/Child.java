package no.itera.springbootdbexample.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
