package com.nitin.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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


    //causes infinite loop-> while get this class it loads user then again user loads this class and so on
    //adds bidirectional one-to-one mapping without adding FKey in db
    //mappedBy value should be eq to this class variable value in user class
    //    @OneToOne(mappedBy = "userTodo", cascade = CascadeType.ALL)
    //    private User user;

    //even though below annotation doesnt return  infinite nested data but hibernate continues to attach entities nested and hence it takes a lot of time.
    //@JsonIgnoreProperties("todos")
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
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
        title = title;
    }

    public void setDescription(String description) {
        description = description;
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
}
