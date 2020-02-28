package com.nitin.todo.controller;

import com.nitin.todo.model.Todo;
import com.nitin.todo.service.TodoHibernateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequestMapping("api/v1/todo")
@RestController
public class TodoController {
    private final TodoHibernateService todoService;

    @Autowired
    public TodoController(TodoHibernateService todoHibernateService) {
        this.todoService = todoHibernateService;
    }

    @PostMapping
    public void createTodo(@RequestBody Todo todo) {
        todoService.createTodo(todo);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping(path = "{id}")
    public Todo getAllTodoById(@PathVariable("id") long id) {
        return todoService.getTodoById(id).orElse(null);
    }

    @PutMapping
    public ResponseEntity updateTodoById(@RequestParam long id, @RequestBody Todo todo) {
        try {
            todoService.updateTodoById(id, todo);
        } catch (Exception e) {
            System.out.println(e);
            HttpHeaders header = new HttpHeaders();
            header.set("Content-Type", "application/json");
            return new ResponseEntity("{\"status\":false}", header, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity("Ok", new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping
    public int deleteTodoById(@RequestParam long id) {
        return todoService.deleteTodoById(id);
    }
}
