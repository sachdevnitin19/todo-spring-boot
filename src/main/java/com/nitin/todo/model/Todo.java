package com.nitin.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String Title;

    @Column(name = "description")
    private String Description;

    public Todo() {

    }

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

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
