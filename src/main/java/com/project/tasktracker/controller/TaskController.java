package com.project.tasktracker.controller;

import com.project.tasktracker.dto.TaskRequest;
import com.project.tasktracker.mappers.TaskMapper;
import com.project.tasktracker.dto.TaskResponse;
import com.project.tasktracker.entity.Task;
import com.project.tasktracker.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAllTasks(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
            ) {

        Page<Task> tasks = taskService.getAllTasks(pageable);

        return ResponseEntity.ok(tasks.map(TaskMapper::entityToResponse));

    }

    @GetMapping("/search")
    public ResponseEntity<Page<TaskResponse>> getTasksBySearch(
        @PageableDefault()
        Pageable pageable, @Param("status") String status, @Param("projectId") Long projectId
    ) {
        Page<Task> tasks = taskService.searchTasks(status, projectId, pageable);

        return ResponseEntity.ok(tasks.map(TaskMapper::entityToResponse));

    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task task = taskService.createTask(TaskMapper.requestToEntity(taskRequest), taskRequest.getProjectId());

        return ResponseEntity.status(HttpStatus.CREATED).body(TaskMapper.entityToResponse(task));
    }


}
