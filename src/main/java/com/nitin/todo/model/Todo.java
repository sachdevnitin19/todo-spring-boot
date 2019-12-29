package com.nitin.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Todo {
    private final UUID id;
    private final String Title;
    private final String Description;

    public Todo(@JsonProperty("id") UUID id, @JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.id = id;
        this.Title = title;
        this.Description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }
}
