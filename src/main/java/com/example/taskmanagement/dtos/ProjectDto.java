package com.example.taskmanagement.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private int projectId;

    @NotBlank(message = "Project Name is Required")
    private String name;

    private String createdDate;
    private String dueDate;
    private String modifiedDate;
    private String priority;
    private List<TodoDto> todos;
}
