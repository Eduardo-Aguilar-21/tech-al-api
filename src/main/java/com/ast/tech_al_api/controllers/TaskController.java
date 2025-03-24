package com.ast.tech_al_api.controllers;

import com.ast.tech_al_api.entities.Task;
import com.ast.tech_al_api.enums.TaskPriority;
import com.ast.tech_al_api.enums.TaskStatus;
import com.ast.tech_al_api.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<Task>> findByAssignedUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.findByAssignedUserId(userId));
    }

    @GetMapping("/assigned-page/{userId}")
    public ResponseEntity<Page<Task>> findByAssignedUserId(@PathVariable Long userId,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(taskService.findByAssignedUserId(userId, pageable));
    }

    @GetMapping("/created-page/{userId}")
    public ResponseEntity<Page<Task>> findByCreatedById(@PathVariable Long userId,
                                                            @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(taskService.findByCreatedById(userId, pageable));
    }

    @GetMapping("/unassigned-page/{groupId}")
    public ResponseEntity<Page<Task>> findByAssignedUserIsNullAndGroupId(@PathVariable Long groupId,
                                                                         @RequestParam(defaultValue = "0") int page,
                                                                         @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(taskService.findByAssignedUserIsNullAndGroupId(groupId, pageable));
    }

    @PostMapping
    public ResponseEntity<Task> saveTask(Task task){
        return ResponseEntity.ok(taskService.saveTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, Task task){
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Task> assignTaskToUser(@PathVariable Long taskId,@PathVariable Long userId){
        return ResponseEntity.ok(taskService.assignTaskToUser(taskId, userId));
    }

    @GetMapping("/priority-page/{taskPriority}")
    public ResponseEntity<Page<Task>> findByPriorityAndGroupId(TaskPriority taskPriority,
                                                                   @PathVariable Long groupId,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(taskService.findByPriorityAndGroupId(taskPriority, groupId, pageable));
    }

    @GetMapping("/status-page/{taskStatus}")
    public ResponseEntity<Page<Task>> findByStatusAndGroupId(TaskStatus taskStatus,
                                                                 @PathVariable Long groupId,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(taskService.findByStatusAndGroupId(taskStatus, groupId, pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
