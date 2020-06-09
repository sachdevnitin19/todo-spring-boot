package com.nitin.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "binary(16)")
    private UUID id;

    @Column(name = "title")
    private String Title;

    @Column(name = "description")
    private String Description;

    public Todo() {

    }

    public Todo(@JsonProperty("title") String title, @JsonProperty("description") String description) {
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

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
