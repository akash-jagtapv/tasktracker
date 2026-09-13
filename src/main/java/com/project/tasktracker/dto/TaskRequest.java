package com.project.tasktracker.dto;

import com.project.tasktracker.entity.Project;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskRequest {
    private Long id;

    @NotBlank(message = "Task title cannot be blank")
    private String title;

    private String description;

    private String status;

    private Project project;
}
