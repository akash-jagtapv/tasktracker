package com.project.tasktracker.service;

import com.project.tasktracker.dto.TaskSummary;
import com.project.tasktracker.entity.Project;
import com.project.tasktracker.entity.Task;
import com.project.tasktracker.exception.ProjectNotFoundException;
import com.project.tasktracker.exception.TaskNotFoundException;
import com.project.tasktracker.repository.ProjectRepository;
import com.project.tasktracker.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    public Page<Task> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public Task createTask(Task task, Long projectId) {
        Project foundProject = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId));

        task.setProject(foundProject);

        return taskRepository.save(task);
    }

    public Page<Task> searchTasks(String status, Long projectId, Pageable pageable) {
        return taskRepository.searchTasks(status, projectId, pageable);
    }

    public Page<TaskSummary> getAllSummaries(Pageable pageable) {
        return taskRepository.findAllSummaries(pageable);
    }

    public Task updateTask(Task updatedTask, Long id) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (updatedTask.getTitle() != null) {
            existingTask.setTitle(updatedTask.getTitle());
        }

        if(updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }

        if(updatedTask.getStatus() != null) {
            existingTask.setDescription(updatedTask.getStatus());
        }

        return taskRepository.save(existingTask);
    }
}
