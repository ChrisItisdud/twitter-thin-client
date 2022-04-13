package com.itisdud;

public class Account {
    private String name;
    private String id;
    private String username;

    public Account(String name, String id, String username) {
        this.name = name;
        this.id = id;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "name " + this.name + " id " + this.id + " username " + this.username;
    }
}
