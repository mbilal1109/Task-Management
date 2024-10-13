package com.example.taskmanagement.services;

import com.example.taskmanagement.dtos.ProjectDto;
import com.example.taskmanagement.dtos.TodoDto;

import java.util.List;

public interface ProjectService {
    ProjectDto createProject(ProjectDto projectDto);
    ProjectDto updateProject(int projectId, ProjectDto projectDto);
    void deleteProject(int projectId);
    List<ProjectDto> getAllProject();
    ProjectDto getProjectById(int projectId);
    List<TodoDto> getAllTodoForProject(int projectId);
    ProjectDto createTodoInProject(int projectId, TodoDto todoDto);
    ProjectDto addExistingTodoInProject(int projectId, List<Integer> todoDtoIds);
}
