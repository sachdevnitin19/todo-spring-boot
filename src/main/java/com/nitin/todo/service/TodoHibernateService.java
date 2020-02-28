package com.nitin.todo.service;

import com.nitin.todo.dao.TodoHibernateDao;
import com.nitin.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
        List<Todo> todoList = todoHibernateDao.findAll();
        return todoList;
    }

    public Optional<Todo> getTodoById(long id) {
        Optional<Todo> todo = todoHibernateDao.findById(id);
        return todo;
    }

    public int updateTodoById(long id, Todo updatedTodo)throws Error {
        Todo todoToUpdate = todoHibernateDao.findById(id).get();
        if (todoToUpdate == null) {
            throw new Error("Todo not found");
        }

        todoToUpdate.setTitle(updatedTodo.getTitle());
        todoToUpdate.setDescription(updatedTodo.getDescription());
        todoHibernateDao.save(todoToUpdate);
        return 0;
    }

    public int deleteTodoById(long id){
        todoHibernateDao.deleteById(id);
        return 0;
    }

}
