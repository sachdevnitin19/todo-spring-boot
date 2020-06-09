package com.nitin.todo.dao;

import com.nitin.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TodoHibernateDao extends JpaRepository<Todo, UUID> {
}
