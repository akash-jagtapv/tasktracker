package com.project.tasktracker.controller;

import com.project.tasktracker.dto.Mapper;
import com.project.tasktracker.dto.TaskResponse;
import com.project.tasktracker.entity.Task;
import com.project.tasktracker.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        return ResponseEntity.ok(tasks.map(Mapper::entityToResponse));

    }

}
