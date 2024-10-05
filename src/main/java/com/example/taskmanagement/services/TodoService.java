package com.example.taskmanagement.services;

import com.example.taskmanagement.dtos.TodoDto;

import java.util.List;

public interface TodoService {
    TodoDto createTodo(TodoDto todoDto);
    TodoDto updateTodo(int todoId, TodoDto todoDto);
    void deleteTodo(int todoId);
    List<TodoDto> getAllTodo();
    TodoDto getTodoById(int todoId);
}
