package com.nitin.todo.service;

import com.nitin.todo.dao.TodoHibernateDao;
import com.nitin.todo.dao.UserDao;
import com.nitin.todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    UserDao userDao;
    TodoHibernateDao todoDao;

    @Autowired
    public UserService(UserDao userDao, TodoHibernateDao todoDao) {
        this.userDao = userDao;
        this.todoDao = todoDao;
    }

    public boolean createNewUser(User user) {
        this.userDao.save(new User(user.getName(), user.getEmail()));
        return true;
    }

    public List<User> getUsers() {
        return this.userDao.findAll();
    }

    public void deleteUserById(UUID id) {
        this.userDao.deleteById(id);
    }

    public User findUserById(UUID userId) {
        Optional<User> user = this.userDao.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchElementException("user not found");
        }
//        List<User.UserProjection> userList=this.userDao.getUserById(userId);

//        System.out.println("user");
//        System.out.println(user);
//        System.out.println(userList.get(0));
//        return userList.get(0);
    }
}
