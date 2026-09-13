package com.project.tasktracker.dto;

import com.project.tasktracker.entity.Task;

public class Mapper {

    public static Task requestToEntity(TaskRequest taskRequest) {
        Task task = new Task();

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus());
        task.getProject().setId(taskRequest.getProjectId());

        return task;
    }

    public static TaskResponse entityToResponse(Task task) {
        TaskResponse taskResponse = new TaskResponse();

        taskResponse.setId(task.getId());
        taskResponse.setTitle(task.getTitle());
        taskResponse.setDescription(task.getDescription());
        taskResponse.setStatus(task.getStatus());
        taskResponse.setProjectId(task.getProject().getId());
        taskResponse.setProjectName(task.getProject().getName());
        taskResponse.setCreatedAt(task.getCreatedAt());
        taskResponse.setUpdatedAt(task.getUpdatedAt());

        return taskResponse;
    }
}
