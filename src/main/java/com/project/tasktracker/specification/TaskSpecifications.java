package com.project.tasktracker.specification;

import com.project.tasktracker.entity.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {

    public static Specification<Task> hasStatus(String status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Task> hasProjectId (Long projectId) {
        return (root, query, cb) ->
                projectId == null ? null : cb.equal(root.get("project").get("id"), projectId);
    }

    public static Specification<Task> titleContains (String keyword) {
        return (root, query, cb) ->
                keyword == null ? null : cb.like(
                        cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"
                );
    }
}
