package com.example.taskmanagement.services.impls;

import com.example.taskmanagement.dtos.ProjectDto;
import com.example.taskmanagement.dtos.TodoDto;
import com.example.taskmanagement.entities.Project;
import com.example.taskmanagement.repositories.ProjectRepository;
import com.example.taskmanagement.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = projectRepository.save(mapper.map(projectDto, Project.class));
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(int projectId, ProjectDto projectDto) {
        Project currentProject = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project with given id not found"));

        currentProject.setName(projectDto.getName());
        currentProject.setPriority(projectDto.getPriority());

        Project updatedProject = projectRepository.save(currentProject);
        return mapper.map(updatedProject, ProjectDto.class);
    }

    @Override
    public void deleteProject(int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project with given id not found"));
        projectRepository.delete(project);
    }

    @Override
    public List<ProjectDto> getAllProject() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDto> projectDtos = projects.stream().map(project -> mapper.map(project, ProjectDto.class)).toList();
        return projectDtos;
    }

    @Override
    public ProjectDto getProjectById(int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project with given id not found"));
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public List<TodoDto> getAllTodoForProject() {
        return null;
    }
}
