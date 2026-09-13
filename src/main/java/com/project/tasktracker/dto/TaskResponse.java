package com.project.tasktracker.dto;

import com.project.tasktracker.entity.Project;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskResponse {

    private Long id;

    private String title;

    private String description;

    private String status;

    private Long projectId;

    private String projectName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
