package com.itera.springbootdbexample.repository;

import java.util.List;

import com.itera.springbootdbexample.model.Child;
import com.itera.springbootdbexample.model.Parent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<Child, Long> {
  List<Child> findByName(String name);
  List<Child> findByParent(Parent name);
}
