package com.nitin.todo.dao;

import com.nitin.todo.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakeTodoDataAccessService implements TodoDao {
    private static List<Todo> DB = new ArrayList<Todo>();

    @Override
    public int createTodo(UUID id, Todo todo) {
        DB.add(new Todo(id, todo.getTitle(), todo.getDescription()));
        return 1;
    }

    @Override
    public List<Todo> getAllTodos() {
        return this.DB;
    }


}
