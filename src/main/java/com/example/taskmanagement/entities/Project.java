package com.example.taskmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(name = "project_name", nullable = false)
    private String name;

    @Column(name = "project_created_date")
    private String createdDate;

    @Column(name = "project_due_date")
    private String dueDate;

    @Column(name = "project_modified_date")
    private String modifiedDate;

    @Column(name = "project_priority")
    private String priority;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private List<Todo> todos;
}
