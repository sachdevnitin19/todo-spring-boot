package com.nitin.todo.service;

import com.nitin.todo.dao.UserDao;
import com.nitin.todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserDao userDao;


    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean createNewUser(User user) {
        this.userDao.save(new User(user.getName(), user.getEmail(), user.getTodo()));
//        this.userDao.save(new User(user.getName(), user.getEmail()));
        return true;
    }

}
