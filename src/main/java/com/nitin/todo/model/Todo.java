package com.nitin.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue//automatically increments the column value
    //need to define uuid as binary(16) else default it gets defined as binary 255 and findById doesnt work
    @Column(name = "id", columnDefinition = "binary(16)")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    //unidirectional relation
    @ManyToOne(
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            optional = false //without user todo cannot be created
    )
    @JoinColumn(name = "user_id")
    private User user;


    public Todo() {
    }

    public Todo(@JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.title = title;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", Title='" + title + '\'' +
                ", Description='" + description + '\'' +
                '}';
    }


    public interface TodoProjection {
        UUID getId();

        String getTitle();

        String getDescription();
    }
}
