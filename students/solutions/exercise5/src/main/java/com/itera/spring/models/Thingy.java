package com.itera.spring.models;

public class Thingy {
    private final Integer id;
    private final String name;

    public Thingy(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
