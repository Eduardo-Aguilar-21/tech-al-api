package com.ast.tech_al_api.services.impl;

import com.ast.tech_al_api.entities.Task;
import com.ast.tech_al_api.enums.TaskPriority;
import com.ast.tech_al_api.enums.TaskStatus;
import com.ast.tech_al_api.repositories.TaskRepository;
import com.ast.tech_al_api.services.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
    }

    @Override
    public List<Task> findByAssignedUserId(Long userId) {
        return taskRepository.findByAssignedUserId(userId);
    }

    @Override
    public Page<Task> findByAssignedUserId(Long userId, Pageable pageable) {
        return taskRepository.findByAssignedUserId(userId, pageable);
    }

    @Override
    public Page<Task> findByCreatedById(Long userId, Pageable pageable) {
        return taskRepository.findByCreatedById(userId, pageable);
    }

    @Override
    public Page<Task> findByAssignedUserIsNullAndGroupId(Long groupId, Pageable pageable) {
        return taskRepository.findByAssignedUserIsNullAndGroupId(groupId, pageable);
    }

    @Override
    public Page<Task> findByPriorityAndGroupId(TaskPriority taskPriority, Long groupId, Pageable pageable) {
        return taskRepository.findByPriorityAndGroupId(taskPriority, groupId, pageable);
    }

    @Override
    public Page<Task> findByStatusAndGroupId(TaskStatus taskStatus, Long groupId, Pageable pageable) {
        return taskRepository.findByStatusAndGroupId(taskStatus, groupId, pageable);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setAssignedUser(task.getAssignedUser());
        return taskRepository.save(existingTask);
    }

    @Override
    public Task assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
        task.getAssignedUser().setId(userId);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}
