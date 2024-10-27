package com.example.taskmanagement.controllers;

import com.example.taskmanagement.commons.ApiResponseMessage;
import com.example.taskmanagement.dtos.ProjectDto;
import com.example.taskmanagement.dtos.TodoDto;
import com.example.taskmanagement.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/{userId}/users")
    public ResponseEntity<ProjectDto> createProject(@PathVariable int userId, @RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.createProject(userId, projectDto);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDto> updateProject(@PathVariable int projectId, @RequestBody ProjectDto projectDto) {
        ProjectDto updatedProject = projectService.updateProject(projectId, projectDto);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<ApiResponseMessage> deleteProject(@PathVariable int projectId) {
        projectService.deleteProject(projectId);

        ApiResponseMessage message = ApiResponseMessage.builder()
                .message("Project Deleted Successfully")
                .status(HttpStatus.OK)
                .success(true)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable int projectId) {
        ProjectDto projectDto = projectService.getProjectById(projectId);
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projectDtos = projectService.getAllProject();
        return new ResponseEntity<>(projectDtos, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/todos")
    public ResponseEntity<List<TodoDto>> getAllTodosFromProject(@PathVariable int projectId) {
        List<TodoDto> todoDtos = projectService.getAllTodoForProject(projectId);
        return new ResponseEntity<>(todoDtos, HttpStatus.OK);
    }

    @PostMapping("/{projectId}")
    public ResponseEntity<ProjectDto> addTodoToProject(@PathVariable int projectId, @Valid @RequestBody TodoDto todoDto) {
        ProjectDto projectDto = projectService.createTodoInProject(projectId, todoDto);
        return new ResponseEntity<>(projectDto, HttpStatus.CREATED);
    }

    /*
        For testing this endpoint, we pass an array with the ids
        Ex: [1, 2, 3]
     */
    @PostMapping("/{projectId}/todos")
    public ResponseEntity<ProjectDto> addExistingTodoToProject(@PathVariable int projectId, @RequestBody List<Integer> todoIds) {
        ProjectDto projectDto = projectService.addExistingTodoInProject(projectId, todoIds);
        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @PutMapping("/{projectId}/todos")
    public ResponseEntity<ApiResponseMessage> removeTodoFromProject(@PathVariable int projectId, @RequestBody List<Integer> todoIds) {
        projectService.removeTodosFromProject(projectId, todoIds);

        ApiResponseMessage message = ApiResponseMessage.builder()
                .message("Todo Removed from Project Successfully")
                .status(HttpStatus.OK)
                .success(true)
                .build();

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
