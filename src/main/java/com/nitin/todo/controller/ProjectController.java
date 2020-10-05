package com.nitin.todo.controller;

import com.nitin.todo.model.Project;
import com.nitin.todo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequestMapping("api/v1/project")
@RestController
public class ProjectController {

    ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService1) {
        this.projectService = projectService1;
    }

    @GetMapping()
    public List<Project> getProjects() {
        return this.projectService.getProjects();
    }

    @GetMapping("{projectId}")
    public ResponseEntity getProjectById(@PathVariable UUID projectId) {
        try {
            return new ResponseEntity(this.projectService.findProjectById(projectId), new HttpHeaders(), HttpStatus.OK);
        } catch (NoSuchElementException ne) {
            return new ResponseEntity("Error", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity createProject(@RequestBody Project project) {
        System.out.println(project);
        this.projectService.createProject(project);
        return new ResponseEntity("Ok", new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{projectId}/addUser/{userId}")
    public ResponseEntity addUserToProject(@PathVariable UUID projectId, @PathVariable UUID userId) {
        try {
            this.projectService.addUserToProject(projectId, userId);
            return new ResponseEntity("Ok", new HttpHeaders(), HttpStatus.OK);
        } catch (NoSuchElementException ne) {
            return new ResponseEntity("Error", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{projectId}/removeUser/{userId}")
    public ResponseEntity deleteUserFromProject(@PathVariable UUID projectId, @PathVariable UUID userId) {
        try {
            this.projectService.removeUserFromProject(projectId, userId);
            return new ResponseEntity("Ok", new HttpHeaders(), HttpStatus.OK);
        } catch (NoSuchElementException ne) {
            return new ResponseEntity("Error", new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
    }

}
