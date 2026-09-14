package com.project.tasktracker.exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task not found with the Id: " + id);
    }
}
