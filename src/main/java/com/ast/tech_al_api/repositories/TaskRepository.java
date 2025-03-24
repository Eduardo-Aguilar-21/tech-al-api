package com.ast.tech_al_api.repositories;

import com.ast.tech_al_api.entities.Task;
import com.ast.tech_al_api.enums.TaskPriority;
import com.ast.tech_al_api.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAssignedUserId(Long userId);
    Page<Task> findByAssignedUserId(Long userId, Pageable pageable);
    Page<Task> findByCreatedById(Long userId, Pageable pageable);
    Page<Task> findByAssignedUserIsNullAndGroupId(Long groupId, Pageable pageable);
    Page<Task> findByPriorityAndGroupId(TaskPriority taskPriority, Long groupId, Pageable pageable);
    Page<Task> findByStatusAndGroupId(TaskStatus taskStatus, Long groupId, Pageable pageable);
}
