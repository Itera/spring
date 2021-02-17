package no.itera.springbootdbexample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import no.itera.springbootdbexample.model.Parent;

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
    List<Parent> items = repository.findAll();

    assertEquals(9, items.size());
  }

  @Test
  void findSingleRecordById() {
    Optional<Parent> item = repository.findById(3L);

    assertTrue(item.isPresent());
    assertEquals("Demo 3", item.get().getName());
  }

  @Test
  void findRecordByName() {
    List<Parent> items = repository.findByName("Demo 2");

    assertEquals(1, items.size());
    assertEquals(2L, items.get(0).getId());
    assertEquals("Demo 2", items.get(0).getName());
  }
}
