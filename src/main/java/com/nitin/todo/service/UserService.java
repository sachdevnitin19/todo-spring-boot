package com.nitin.todo.service;

import com.nitin.todo.dao.TodoHibernateDao;
import com.nitin.todo.dao.UserDao;
import com.nitin.todo.model.Todo;
import com.nitin.todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    UserDao userDao;
    TodoHibernateDao todoDao;

    @Autowired
    public UserService(UserDao userDao, TodoHibernateDao todoDao) {
        this.userDao = userDao;
        this.todoDao=todoDao;
    }

    public boolean createNewUser(User user) {
        Todo reqTodo=user.getTodo();
        Todo userTodo=new Todo(reqTodo.getTitle(),reqTodo.getDescription());
        this.todoDao.save(userTodo);
        this.userDao.save(new User(user.getName(), user.getEmail(), userTodo));
        return true;
    }

    public List<User> getUsers(){
        return this.userDao.findAll();
    }

    public void deleteUserById(UUID id){
        this.userDao.deleteById(id);
    }
}
