package com.nitin.todo.dao;

import com.nitin.todo.model.Todo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeTodoDataAccessService implements TodoDao {
    private static List<Todo> DB = new ArrayList<>();

    @PostConstruct
    public void afterCreating(){

        System.out.println("'fakedao' created");
    }

    @PreDestroy
    public void beforeDestroying(){
        System.out.println("'fakedao' destroyed");
    }

    @Override
    public int createTodo(UUID id, Todo todo) {
        Todo newTodo=new Todo(id, todo.getTitle(), todo.getDescription());
        DB.add(newTodo);
        return 1;
    }

    @Override
    public List<Todo> getAllTodos() {
        return this.DB;
    }

    @Override
    public Optional<Todo> getTodoById(UUID id) {
        return DB.stream()
                .filter(todo->todo.getId().equals(id))
                .findFirst();
    }

    @Override
    public int updateTodoById(UUID id, Todo todo) {
        int dbSize = DB.size();
        for (int i = 0; i < dbSize; i++) {
            Todo currTodo = DB.get(i);
            if (currTodo.getId().equals(id)) {
                DB.set(i, todo);
                break;
            }
        }
        return 1;
    }

    @Override
    public int deleteTodoById(UUID id) {
        int dbSize = DB.size();
        for (int i = 0; i < dbSize; i++) {
            Todo currTodo = DB.get(i);
            if (currTodo.getId().equals(id)) {
                DB.remove(i);
                break;
            }
        }
        return 1;
    }


}
