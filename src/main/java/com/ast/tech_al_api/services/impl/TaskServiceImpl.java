package com.ast.tech_al_api.services.impl;

import com.ast.tech_al_api.entities.TaskEntity;
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
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));
    }

    @Override
    public List<TaskEntity> findByAssignedUserId(Long userId) {
        return taskRepository.findByAssignedUserId(userId);
    }

    @Override
    public Page<TaskEntity> findByAssignedUserId(Long userId, Pageable pageable) {
        return taskRepository.findByAssignedUserId(userId, pageable);
    }

    @Override
    public Page<TaskEntity> findByCreatedById(Long userId, Pageable pageable) {
        return taskRepository.findByCreatedById(userId, pageable);
    }

    @Override
    public Page<TaskEntity> findByAssignedUserIsNullAndGroupId(Long groupId, Pageable pageable) {
        return taskRepository.findByAssignedUserIsNullAndGroupId(groupId, pageable);
    }

    @Override
    public Page<TaskEntity> findByPriorityAndGroupId(TaskPriority taskPriority, Long groupId, Pageable pageable) {
        return taskRepository.findByPriorityAndGroupId(taskPriority, groupId, pageable);
    }

    @Override
    public Page<TaskEntity> findByStatusAndGroupId(TaskStatus taskStatus, Long groupId, Pageable pageable) {
        return taskRepository.findByStatusAndGroupId(taskStatus, groupId, pageable);
    }

    @Override
    public TaskEntity saveTask(TaskEntity taskEntity) {
        //System.out.println("Creado por " + taskEntity.getCreatedBy());
        return taskRepository.save(taskEntity);
    }

    @Override
    public TaskEntity updateTask(Long id, TaskEntity taskEntity) {
        TaskEntity existingTaskEntity = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + id));

        existingTaskEntity.setTitle(taskEntity.getTitle());
        existingTaskEntity.setDescription(taskEntity.getDescription());
        existingTaskEntity.setAssignedUser(taskEntity.getAssignedUser());
        return taskRepository.save(existingTaskEntity);
    }

    @Override
    public TaskEntity assignTaskToUser(Long taskId, Long userId) {
        TaskEntity taskEntity = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found with id: " + taskId));
        taskEntity.getAssignedUser().setId(userId);
        return taskRepository.save(taskEntity);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
}
