package com.project.hci.ajou.leehyolee;

public class Task {
    private String name;
    private int id;

    public Task(String auto) {
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public Task(int id, String name)
    {
        this.id = id;
        this.name = name;
    }
}
