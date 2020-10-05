package com.nitin.todo.service;

import com.nitin.todo.dao.ProjectHibernateDao;
import com.nitin.todo.dao.UserDao;
import com.nitin.todo.model.Project;
import com.nitin.todo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {
    ProjectHibernateDao projectHibernateDao;
    UserDao userDao;

    @Autowired
    public ProjectService(ProjectHibernateDao projectHibernateDao1, UserDao userDao1) {
        this.projectHibernateDao = projectHibernateDao1;
        this.userDao = userDao1;
    }

    public List<Project> getProjects() {
        return this.projectHibernateDao.findAll();
    }

    public Project findProjectById(UUID projectId) throws NoSuchElementException {
        Optional<Project> projectOptional = this.projectHibernateDao.findById(projectId);
        if (!projectOptional.isPresent()) {
            throw new NoSuchElementException();
        }
        Project project = projectOptional.get();
        project.setUsers(project.getUsers());
        return project;
    }

    public boolean createProject(Project project) {
        Project newProject = new Project(project.getName(), project.getDescription());
        this.projectHibernateDao.save(newProject);
        return true;
    }

    public Optional<Project> getProjectById(UUID projectId) {
        return this.projectHibernateDao.findById(projectId);
    }

    public boolean addUserToProject(UUID projectId, UUID userId) throws NoSuchElementException {
        Optional<Project> project = this.getProjectById(projectId);
        if (!project.isPresent()) {
            throw new NoSuchElementException();
        }
        System.out.println(project.get());
        Optional<User> user = this.userDao.findById(userId);
        if (!user.isPresent()) {
            throw new NoSuchElementException();
        }

        project.get().addUser(user.get());
        this.projectHibernateDao.save(project.get());
        return true;
    }

    public boolean removeUserFromProject(UUID projectId, UUID userId) throws NoSuchElementException {
        Optional<Project> projectOptional = this.getProjectById(projectId);
        if (!projectOptional.isPresent()) {
            throw new NoSuchElementException();
        }
        System.out.println(projectOptional.get());
        Optional<User> userOptional = this.userDao.findById(userId);
        if (!userOptional.isPresent()) {
            throw new NoSuchElementException();
        }

        Project project = projectOptional.get();
        project.removeUser(userOptional.get());
        this.projectHibernateDao.save(project);
        return true;
    }
}
