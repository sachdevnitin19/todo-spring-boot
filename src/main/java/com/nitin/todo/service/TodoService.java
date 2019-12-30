package com.nitin.todo.service;

import com.nitin.todo.dao.TodoDao;
import com.nitin.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoService {

    private final TodoDao todoDao;

    @Autowired
    public TodoService(@Qualifier("fakeDao") TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public int createTodo(Todo todo) {
        return todoDao.createTodo(todo);
    }

    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    public Optional<Todo> getTodoById(UUID id){
        return todoDao.getTodoById(id);
    }

    public int updateTodoById(UUID id, Todo todo) {
        return todoDao.updateTodoById(id, todo);
    }

    public int deleteTodoById(UUID id){
        return todoDao.deleteTodoById(id);
    }
}
