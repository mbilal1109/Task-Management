package com.example.taskmanagement.controllers;

import com.example.taskmanagement.commons.ApiResponseMessage;
import com.example.taskmanagement.dtos.TodoDto;
import com.example.taskmanagement.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        TodoDto createdTodo = todoService.createTodo(todoDto);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable int todoId, @RequestBody TodoDto todoDto) {
        TodoDto updatedTodo = todoService.updateTodo(todoId, todoDto);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable int todoId) {
        TodoDto todoDto = todoService.getTodoById(todoId);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo() {
        List<TodoDto> todoDtos = todoService.getAllTodo();
        return new ResponseEntity<>(todoDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<ApiResponseMessage> deleteTodo(@PathVariable int todoId) {
        todoService.deleteTodo(todoId);

        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Todo Deleted Successfully")
                .status(HttpStatus.OK)
                .success(true)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
