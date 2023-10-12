package com.itera.springbootdbexample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import com.itera.springbootdbexample.model.Child;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ChildRepositoryIT {
  @Autowired
  ParentRepository parentRepository;

  @Autowired
  ChildRepository repository;

  @Test
  void dataWasLoaded() {
    List<Child> children = repository.findAll();

    assertEquals(5, children.size());
  }

  @Test
  void findSingleRecordById() {
    Optional<Child> child = repository.findById(3L);

    assertTrue(child.isPresent());
    assertEquals("Demo Child 3", child.get().getName());
    assertEquals("Demo 1", child.get().getParent().getName());
  }

  @Test
  void findRecordByName() {
    List<Child> children = repository.findByName("Demo Child 4");

    assertEquals(1, children.size());
    assertEquals(4L, children.get(0).getId());
    assertEquals("Demo Child 4", children.get(0).getName());
    assertEquals("Demo 2", children.get(0).getParent().getName());
  }
}
