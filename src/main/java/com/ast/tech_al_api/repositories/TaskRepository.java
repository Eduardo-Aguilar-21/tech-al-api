package com.ast.tech_al_api.repositories;

import com.ast.tech_al_api.entities.TaskEntity;
import com.ast.tech_al_api.enums.TaskPriority;
import com.ast.tech_al_api.enums.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByAssignedUserId(Long userId);
    Page<TaskEntity> findByAssignedUserId(Long userId, Pageable pageable);
    Page<TaskEntity> findByCreatedById(Long userId, Pageable pageable);
    Page<TaskEntity> findByAssignedUserIsNullAndGroupId(Long groupId, Pageable pageable);
    Page<TaskEntity> findByPriorityAndGroupId(TaskPriority taskPriority, Long groupId, Pageable pageable);
    Page<TaskEntity> findByStatusAndGroupId(TaskStatus taskStatus, Long groupId, Pageable pageable);
}
