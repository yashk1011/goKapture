package com.taskmanagement.ass.Services;

import ch.qos.logback.core.status.Status;
import com.taskmanagement.ass.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    public Optional<Task> getTaskById(Long id, Long userId) {
        return taskRepository.findByIdAndUserId(id, userId);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setPriority(taskDetails.getPriority());
        task.setDueDate(taskDetails.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id, Long userId) {
        Task task = taskRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }

    public List<Task> filterTasksByStatus(Long userId, Status status) {
        return taskRepository.findByUserIdAndStatus(userId, status);
    }

    public List<Task> filterTasksByPriority(Long userId, int priority) {
        return taskRepository.findByUserIdAndPriority(userId, priority);
    }

    public List<Task> filterTasksByDueDate(Long userId, LocalDateTime dueDate) {
        return taskRepository.findByUserIdAndDueDateBefore(userId, dueDate);
    }

    public List<Task> searchTasks(Long userId, String keyword) {
        return taskRepository.findByUserIdAndTitleContainingOrDescriptionContaining(userId, keyword, keyword);
    }
}