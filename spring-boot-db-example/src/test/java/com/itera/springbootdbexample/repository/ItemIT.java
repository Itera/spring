package com.itera.springbootdbexample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.itera.springbootdbexample.model.Item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class ItemIT {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @Test
  void testQuery() {
    String query = "SELECT * FROM demo_item WHERE id = ?";

    Item item = jdbcTemplate.queryForObject(query, new ItemRowMapper(), 3);

    assertEquals("Demo 3", item.getName());
  }
}
