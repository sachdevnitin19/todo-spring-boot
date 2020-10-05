package com.nitin.todo.dao;

import com.nitin.todo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectHibernateDao extends JpaRepository<Project, UUID> {
}
