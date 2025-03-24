package com.ast.tech_al_api.services;

import com.ast.tech_al_api.entities.Task;
import com.ast.tech_al_api.enums.TaskPriority;
import com.ast.tech_al_api.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    List<Task> findByAssignedUserId(Long userId);
    Page<Task> findByAssignedUserId(Long userId, Pageable pageable);
    Page<Task> findByCreatedById(Long userId, Pageable pageable);
    Page<Task> findByAssignedUserIsNullAndGroupId(Long groupId, Pageable pageable);
    Task saveTask(Task task);
    Task updateTask(Long id, Task task);
    Task assignTaskToUser(Long taskId, Long userId);
    Page<Task> findByPriorityAndGroupId(TaskPriority taskPriority, Long groupId , Pageable pageable);
    Page<Task> findByStatusAndGroupId(TaskStatus taskStatus, Long groupId , Pageable pageable);
    void deleteTask(Long id);
}
