package com.example.taskmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoId;

    @Column(name = "todo_title")
    private String title;

    @Column(name = "todo_description")
    private String description;

    @Column(name = "todo_created_date")
    private String createdDate;

    @Column(name = "todo_due_date")
    private String dueDate;

    @Column(name = "todo_modified_date")
    private String modifiedDate;

    @Column(name = "todo_priority")
    private String priority; // LOW, MEDIUM, HIGH

    @Column(name = "todo_status")
    private String status; // PENDING, IN-PROGRESS, COMPLETED

    @Column(name = "todo_notes")
    private String notes;
}
