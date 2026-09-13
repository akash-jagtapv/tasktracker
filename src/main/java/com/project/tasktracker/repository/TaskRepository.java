package com.project.tasktracker.repository;

import com.project.tasktracker.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE " +
                    "(:status IS NULL OR t.status = :status) AND " +
                    "(:projectId IS NULL OR t.project.id = :projectId)"
    )
    Page<Task> searchTasks(
            @Param("status") String status,
            @Param("projectId") Long projectId,
            Pageable pageable
    );
}
