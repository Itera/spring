package com.itera.springbootdbexample.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.itera.springbootdbexample.model.Item;

import org.springframework.jdbc.core.RowMapper;

public class ItemRowMapper implements RowMapper<Item> {
  @Override
  public Item mapRow(ResultSet resultSet, int i) throws SQLException {
    Item item = new Item();

    item.setId(resultSet.getLong("id"));
    item.setName(resultSet.getString("name"));

    return item;
  }
}
