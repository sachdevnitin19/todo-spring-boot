package com.nitin.todo.dao;

import java.util.List;
import java.util.UUID;
import com.nitin.todo.model.Todo;

public interface TodoDao {

    int createTodo(UUID id, Todo todo);

    default int createTodo(Todo todo){
        UUID id=UUID.randomUUID();
        return createTodo(id,todo);
    };

    List<Todo> getAllTodos();
}
