package com.example.taskmanagement.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Todo Title is Required")
    private String title;

    @NotBlank(message = "Todo Description is Required")
    private String description;

    private String createdDate;
    private String dueDate;
    private String modifiedDate;
    private String priority;
    private String status;
    private String notes;
}
