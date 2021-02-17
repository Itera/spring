package no.itera.springbootdbexample.repository;

import java.util.List;

import no.itera.springbootdbexample.model.Parent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
  List<Parent> findByName(String name);
}
