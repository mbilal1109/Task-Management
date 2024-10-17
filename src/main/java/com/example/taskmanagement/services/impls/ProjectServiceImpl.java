package com.example.taskmanagement.services.impls;

import com.example.taskmanagement.dtos.ProjectDto;
import com.example.taskmanagement.dtos.TodoDto;
import com.example.taskmanagement.entities.Project;
import com.example.taskmanagement.entities.Todo;
import com.example.taskmanagement.repositories.ProjectRepository;
import com.example.taskmanagement.repositories.TodoRepository;
import com.example.taskmanagement.services.ProjectService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TodoRepository todoRepository;

    private Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Date createdDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(createdDate);
        projectDto.setCreatedDate(dateString);

        Project project = projectRepository.save(mapper.map(projectDto, Project.class));
        return mapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto updateProject(int projectId, ProjectDto projectDto) {
        Project currentProject = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project with given id not found"));

        Date modifiedDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(modifiedDate);
        currentProject.setModifiedDate(dateString);

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
    public List<TodoDto> getAllTodoForProject(int projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project with given id not found"));
        List<Todo> todos = project.getTodos();
        List<TodoDto> todoDtos = todos.stream().map(todo -> mapper.map(todo, TodoDto.class)).toList();
        return todoDtos;
    }

    @Override
    public ProjectDto createTodoInProject(int projectId, TodoDto todoDto) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project with given id not found"));

        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setPriority(todoDto.getPriority());
        todo.setStatus(todoDto.getStatus());
        todo.setNotes(todoDto.getNotes());

        Date createdDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(createdDate);
        todo.setCreatedDate(dateString);

        project.getTodos().add(todo);
        Project updatedProject = projectRepository.save(project);

        return mapper.map(updatedProject, ProjectDto.class);
    }

    @Override
    public ProjectDto addExistingTodoInProject(int projectId, List<Integer> todoDtoIds) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project with given id not found"));

        for(Integer id : todoDtoIds) {
            Todo todoFound = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo with given id not found"));
            project.getTodos().add(todoFound);
        }

        Project updatedProject = projectRepository.save(project);
        return mapper.map(updatedProject, ProjectDto.class);
    }

    @Override
    public void removeTodoFromProject(int projectId, int todoId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project with given id not found"));
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new RuntimeException("Todo with given id not found"));

        project.getTodos().remove(todo);
        logger.info(project.toString());
        projectRepository.save(project);
    }
}
