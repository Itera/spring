package com.itera.springbootdbexample.repository;

import java.util.List;

import com.itera.springbootdbexample.model.Parent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent, Long> {
  List<Parent> findByName(String name);
}
