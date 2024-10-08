package com.example.taskmanagement.services.impls;

import com.example.taskmanagement.dtos.TodoDto;
import com.example.taskmanagement.entities.Todo;
import com.example.taskmanagement.repositories.TodoRepository;
import com.example.taskmanagement.services.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Date createdDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(createdDate);
        todoDto.setCreatedDate(dateString);

        Todo todo = todoRepository.save(mapper.map(todoDto, Todo.class));
        return mapper.map(todo, TodoDto.class);
    }

    @Override
    public TodoDto updateTodo(int todoId, TodoDto todoDto) {
        Todo currentTodo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Todo with given id not found"));

        Date modifiedDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(modifiedDate);
        currentTodo.setModifiedDate(dateString);

        currentTodo.setTitle(todoDto.getTitle());
        currentTodo.setDescription(todoDto.getDescription());
        currentTodo.setDueDate(todoDto.getDueDate());
        currentTodo.setPriority(todoDto.getPriority());
        currentTodo.setStatus(todoDto.getStatus());
        currentTodo.setNotes(todoDto.getNotes());

        Todo updatedTodo = todoRepository.save(currentTodo);
        return mapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(int todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Todo with given id not found"));
        todoRepository.delete(todo);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> todos = todoRepository.findAll();
        List<TodoDto> todoDtos = todos.stream().map(todo -> mapper.map(todo, TodoDto.class)).toList();
        return todoDtos;
    }

    @Override
    public TodoDto getTodoById(int todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Todo with given id not found"));
        return mapper.map(todo, TodoDto.class);
    }
}
