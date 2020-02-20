package com.nitin.todo.service;

import com.nitin.todo.dao.TodoHibernateDao;
import com.nitin.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoHibernateService {
    TodoHibernateDao todoHibernateDao;

    @Autowired
    public TodoHibernateService(TodoHibernateDao todoDao) {
        this.todoHibernateDao = todoDao;
    }

    public int createTodo(Todo todo) {
        UUID id = UUID.randomUUID();
        this.todoHibernateDao.save(new Todo(id, todo.getTitle(), todo.getDescription()));
        return 0;
    }

    public List<Todo> getAllTodos() {
        List<Todo> todoList = todoHibernateDao.findAll();
        return todoList;
    }

    public Optional<Todo> getTodoById(UUID id) {
        Optional<Todo> todo = todoHibernateDao.findById(id);
        System.out.println(todo.toString());
        return todo;
    }
}
