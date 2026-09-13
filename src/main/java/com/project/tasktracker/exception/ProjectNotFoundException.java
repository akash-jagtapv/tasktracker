package com.project.tasktracker.exception;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(Long projectId) {
        super("Project not found with Id: " + projectId);
    }
}
