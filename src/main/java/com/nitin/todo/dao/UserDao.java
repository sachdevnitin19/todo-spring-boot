package com.nitin.todo.dao;

import com.nitin.todo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID> {
    @Query("SELECT u.id, u.email, u.name FROM User u where u.id=?1")
    List<User.UserProjection> getUserById(UUID userId);
}
