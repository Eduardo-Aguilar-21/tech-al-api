package com.ast.tech_al_api.services;

import com.ast.tech_al_api.entities.TaskEntity;
import com.ast.tech_al_api.enums.TaskPriority;
import com.ast.tech_al_api.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    List<TaskEntity> getAllTasks();
    TaskEntity getTaskById(Long id);
    List<TaskEntity> findByAssignedUserId(Long userId);
    Page<TaskEntity> findByAssignedUserId(Long userId, Pageable pageable);
    Page<TaskEntity> findByCreatedById(Long userId, Pageable pageable);
    Page<TaskEntity> findByAssignedUserIsNullAndGroupId(Long groupId, Pageable pageable);
    TaskEntity saveTask(TaskEntity taskEntity);
    TaskEntity updateTask(Long id, TaskEntity taskEntity);
    TaskEntity assignTaskToUser(Long taskId, Long userId);
    Page<TaskEntity> findByPriorityAndGroupId(TaskPriority taskPriority, Long groupId , Pageable pageable);
    Page<TaskEntity> findByStatusAndGroupId(TaskStatus taskStatus, Long groupId , Pageable pageable);
    void deleteTask(Long id);
}
