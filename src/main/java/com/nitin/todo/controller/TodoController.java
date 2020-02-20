package com.nitin.todo.controller;

import com.nitin.todo.model.Todo;
import com.nitin.todo.service.TodoHibernateService;
import com.nitin.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/todo")
@RestController
public class TodoController {
//    private final TodoService todoService;
    private final TodoHibernateService todoService;
    @Autowired
    public TodoController(TodoService todoService, TodoHibernateService todoHibernateService) {
//        this.todoService = todoService;
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
    public Todo getAllTodoById(@PathVariable("id") UUID id) {
        return todoService.getTodoById(id).orElse(null);
    }
//
//    @PatchMapping
//    public int updateTodoById(@RequestParam UUID id, @RequestBody Todo todo) {
//        return todoService.updateTodoById(id, todo);
//    }
//
//    @DeleteMapping
//    public int deleteTodoById(@RequestParam UUID id) {
//        return todoService.deleteTodoById(id);
//    }
}
