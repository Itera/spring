package com.itera.springbootdbexample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.itera.springbootdbexample.model.Parent;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ParentRepositoryIT {
  @Autowired
  ParentRepository repository;

  @Test
  void dataWasLoaded() {
    List<Parent> parents = repository.findAll();

    assertEquals(9, parents.size());
  }

  @Test
  void findSingleRecordById() {
    Optional<Parent> parent = repository.findById(3L);

    assertTrue(parent.isPresent());
    assertEquals("Demo 3", parent.get().getName());
  }

  @Test
  void findRecordByName() {
    List<Parent> parents = repository.findByName("Demo 2");

    assertEquals(1, parents.size());
    assertEquals(2L, parents.get(0).getId());
    assertEquals("Demo 2", parents.get(0).getName());
  }

  @Test
  void childrenFound() {
    Optional<Parent> parent = repository.findById(1L);

    assertTrue(parent.isPresent());
    assertEquals(3, parent.get().getChildren().size());

    parent = repository.findById(2L);

    assertTrue(parent.isPresent());
    assertEquals(2, parent.get().getChildren().size());

    parent = repository.findById(3L);

    assertTrue(parent.isPresent());
    assertEquals(0, parent.get().getChildren().size());
  }
}
