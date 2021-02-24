package no.itera.springbootdbexample.repository;

import java.util.List;

import no.itera.springbootdbexample.model.Child;
import no.itera.springbootdbexample.model.Parent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
  List<Child> findByName(String name);
  List<Child> findByParent(Parent name);
}
