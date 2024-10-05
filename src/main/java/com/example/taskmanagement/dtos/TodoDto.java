package com.example.taskmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private int todoId;
    private String title;
    private String description;
    private Date createdDate;
    private Date dueDate;
    private Date modifiedDate;
    private String priority;
    private String status;
    private String notes;
}
