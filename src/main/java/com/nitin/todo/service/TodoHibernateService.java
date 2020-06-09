package com.nitin.todo.service;

import com.nitin.todo.dao.TodoHibernateDao;
import com.nitin.todo.model.Todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TodoHibernateService {
    TodoHibernateDao todoHibernateDao;

    @Autowired
    public TodoHibernateService(TodoHibernateDao todoDao) {
        this.todoHibernateDao = todoDao;
    }

    public int createTodo(Todo todo) {
        this.todoHibernateDao.save(new Todo(todo.getTitle(), todo.getDescription()));
        return 0;
    }

    public List<Todo> getAllTodos() {

        Iterator<Todo> todoItr = todoHibernateDao.findAll().iterator();
        List<Todo> todoList = new ArrayList<>();
        while (todoItr.hasNext())
            todoList.add(todoItr.next());

        return todoList;

    }

    public Optional<Todo> getTodoById(UUID id) {
        return todoHibernateDao.findById(id);
    }

    public int updateTodoById(UUID id, Todo updatedTodo) throws Exception {
        if (todoHibernateDao.findById(id).isPresent()) {
            Todo todoToUpdate = todoHibernateDao.findById(id).get();
            todoToUpdate.setTitle(updatedTodo.getTitle());
            todoToUpdate.setDescription(updatedTodo.getDescription());
            todoHibernateDao.save(todoToUpdate);
            return 0;
        } else {
            throw new Exception("Todo not found");
        }
    }

    public int deleteTodoById(UUID id) throws NoSuchElementException {
        if (todoHibernateDao.findById(id).isPresent()) {
            todoHibernateDao.deleteById(id);
        } else {
            throw new NoSuchElementException("Todo not found");
        }
        return 0;
    }

}
