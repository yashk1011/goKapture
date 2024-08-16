package com.taskmanagement.ass.Repositories;

import ch.qos.logback.core.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdAndStatus(Long userId, Status status);
    List<Task> findByUserIdAndPriority(Long userId, int priority);
    List<Task> findByUserIdAndDueDateBefore(Long userId, LocalDateTime dueDate);
    List<Task> findByUserIdAndTitleContainingOrDescriptionContaining(Long userId, String title, String description);
}