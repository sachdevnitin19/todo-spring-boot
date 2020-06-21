package com.nitin.todo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "binary(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name = "user_todo")
    private Todo userTodo;

    public User() {

    }
    //do not create another constructor with @JsonProperty, it will throw error not accepting json
//    public User(@JsonProperty("name") String name, @JsonProperty("email") String email) {
//        this.name = name;
//        this.email = email;
//    }

    public User(@JsonProperty("name") String name, @JsonProperty("email") String email, @JsonProperty("todo") Todo todo) {
        this.name = name;
        this.email = email;
        this.userTodo = todo;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Todo getTodo() {
        return userTodo;
    }

    public void setTodo(Todo todo) {
        this.userTodo = todo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", todo=" + userTodo +
                '}';
    }
}
