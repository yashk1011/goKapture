package com.taskmanagement.ass.Controllers;

import ch.qos.logback.core.status.Status;
import com.taskmanagement.ass.Services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(@RequestParam Long userId) {
        return taskService.getAllTasks(userId);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id, @RequestParam Long userId) {
        return taskService.getTaskById(id, userId).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskService.updateTask(id, taskDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id, @RequestParam Long userId) {
        taskService.deleteTask(id, userId);
    }

    @GetMapping("/filter/status")
    public List<Task> filterTasksByStatus(@RequestParam Long userId, @RequestParam Status status) {
        return taskService.filterTasksByStatus(userId, status);
    }

    @GetMapping("/filter/priority")
    public List<Task> filterTasksByPriority(@RequestParam Long userId, @RequestParam int priority) {
        return taskService.filterTasksByPriority(userId, priority);
    }

    @GetMapping("/filter/duedate")
    public List<Task> filterTasksByDueDate(@RequestParam Long userId, @RequestParam LocalDateTime dueDate) {
        return taskService.filterTasksByDueDate(userId, dueDate);
    }

    @GetMapping("/search")
    public List<Task> searchTasks(@RequestParam Long userId, @RequestParam String keyword) {
        return taskService.searchTasks(userId, keyword);
    }
}