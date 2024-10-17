package com.example.taskmanagement.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int todoId;

    @Column(name = "todo_title", nullable = false)
    private String title;

    @Column(name = "todo_description", nullable = false)
    private String description;

    @Column(name = "todo_created_date")
    private String createdDate;

    @Column(name = "todo_due_date")
    private String dueDate;

    @Column(name = "todo_modified_date")
    private String modifiedDate;

    @Column(name = "todo_priority")
    private String priority;

    @Column(name = "todo_status")
    private String status;

    @Column(name = "todo_notes")
    private String notes;
}
