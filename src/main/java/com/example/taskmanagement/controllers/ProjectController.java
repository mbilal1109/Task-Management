package com.example.taskmanagement.controllers;

import com.example.taskmanagement.commons.ApiResponseMessage;
import com.example.taskmanagement.dtos.ProjectDto;
import com.example.taskmanagement.services.ProjectService;
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

    @PostMapping
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto) {
        ProjectDto createdProject = projectService.createProject(projectDto);
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
}
