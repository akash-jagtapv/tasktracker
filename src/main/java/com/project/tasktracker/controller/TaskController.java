package com.project.tasktracker.controller;

import com.project.tasktracker.mappers.TaskMapper;
import com.project.tasktracker.dto.*;
import com.project.tasktracker.entity.Task;
import com.project.tasktracker.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedModel;
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
    public ResponseEntity<PagedModel<TaskResponse>> getAllTasks(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
            ) {

        Page<Task> tasks = taskService.getAllTasks(pageable);

        return ResponseEntity.ok(new PagedModel<>(tasks.map(TaskMapper::entityToResponse)));

    }

    @GetMapping("/search")
    public ResponseEntity<PagedModel<TaskResponse>> getTasksBySearch(
        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
        Pageable pageable, @RequestParam(required = false) String status, @RequestParam(required = false) Long projectId
    ) {
        Page<Task> tasks = taskService.searchTasks(status, projectId, pageable);

        return ResponseEntity.ok(new PagedModel<>(tasks.map(TaskMapper::entityToResponse)));

    }

    @GetMapping("/summary")
    public ResponseEntity<PagedModel<TaskSummary>> findAllSummaries(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC)
            Pageable pageable
    ) {
        Page<TaskSummary> taskSummary = taskService.getAllSummaries(pageable);

        return ResponseEntity.ok(new PagedModel<>(taskSummary));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        Task task = taskService.createTask(TaskMapper.requestToEntity(taskRequest), taskRequest.getProjectId());

        return ResponseEntity.status(HttpStatus.CREATED).body(TaskMapper.entityToResponse(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@Valid @RequestBody TaskRequest taskRequest, @PathVariable("id") Long id) {
        Task updatedTask = taskService.updateTask(TaskMapper.requestToEntity(taskRequest), id);

        return ResponseEntity.status(HttpStatus.OK).body(TaskMapper.entityToResponse(updatedTask));
    }

}
