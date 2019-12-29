package com.nitin.todo.service;

import com.nitin.todo.dao.TodoDao;
import com.nitin.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Todo> getAllTodos(){
        return todoDao.getAllTodos();
    }
}
