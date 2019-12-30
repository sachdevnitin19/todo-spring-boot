package com.nitin.todo.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.nitin.todo.model.Todo;

public interface TodoDao {

    int createTodo(UUID id, Todo todo);

    default int createTodo(Todo todo) {
        UUID id = UUID.randomUUID();
        return createTodo(id, todo);
    }

    List<Todo> getAllTodos();

    Optional<Todo> getTodoById(UUID id);

    int updateTodoById(UUID id,Todo todo);

    int deleteTodoById(UUID id);
}
