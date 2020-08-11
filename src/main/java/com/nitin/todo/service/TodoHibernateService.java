package com.nitin.todo.service;

import com.nitin.todo.dao.TodoHibernateDao;
import com.nitin.todo.dao.UserDao;
import com.nitin.todo.model.Todo;

import com.nitin.todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class TodoHibernateService {
    TodoHibernateDao todoHibernateDao;
    UserDao userDao;
    UserService userService;

    @Autowired
    public TodoHibernateService(TodoHibernateDao todoDao, UserDao userDao, UserService userService) {
        this.todoHibernateDao = todoDao;
        this.userDao = userDao;
        this.userService = userService;
    }

    public int createTodo(UUID userId, Todo todoObj) {
        Optional<User> user = this.userDao.findById(userId);
        if (user.isPresent()) {
            Todo todoToSave = new Todo(todoObj.getTitle(), todoObj.getDescription());
            todoToSave.setUser(user.get());
            this.todoHibernateDao.save(todoToSave);
        }
        return 0;
    }

    public List<Todo> getAllTodos() {
        return todoHibernateDao.findAll();
    }

    public List<Todo.TodoProjection> getAllTodoByUserId(UUID userId) {
        System.out.println(userId.getClass());
        System.out.println(userId);
        User user = this.userService.findUserById(userId);
        if (user == null) {
            return null;
        }
        System.out.println(user);

        return todoHibernateDao.findAllByUser(user);
    }

    public List<Todo> searchTodoByTitle(String title) {
        return todoHibernateDao.searchByTitle(title);
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
