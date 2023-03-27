package com.jsonbook.Json.Book.controller;

public class SimpleUser {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public SimpleUser(){}
    public SimpleUser(long id, String email) {
        this.id = id;
        this.name = email;
    }

    // getters and setters omitted for brevity
}
