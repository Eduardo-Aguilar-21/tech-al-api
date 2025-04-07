package com.ast.tech_al_api.controllers;

import com.ast.tech_al_api.entities.TaskEntity;
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

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/priorities")
    public List<String> getTaskPriorities() {
        return Arrays.asList(TaskPriority.values()) // Devuelve todos los valores del enum TaskPriority
                .stream()
                .map(Enum::name) // Mapea a los nombres de las prioridades como Strings
                .toList();
    }

    @GetMapping("/statuses")
    public List<TaskStatus> getTaskStatuses() {
        return Arrays.asList(TaskStatus.values());
    }

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<TaskEntity>> findByAssignedUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.findByAssignedUserId(userId));
    }

    @GetMapping("/assigned-page/{userId}")
    public ResponseEntity<Page<TaskEntity>> findByAssignedUserId(@PathVariable Long userId,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(taskService.findByAssignedUserId(userId, pageable));
    }

    @GetMapping("/created-page/{userId}")
    public ResponseEntity<Page<TaskEntity>> findByCreatedById(@PathVariable Long userId,
                                                              @RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(taskService.findByCreatedById(userId, pageable));
    }

    @GetMapping("/unassigned-page/{groupId}")
    public ResponseEntity<Page<TaskEntity>> findByAssignedUserIsNullAndGroupId(@PathVariable Long groupId,
                                                                               @RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(taskService.findByAssignedUserIsNullAndGroupId(groupId, pageable));
    }

    @PostMapping
    public ResponseEntity<TaskEntity> saveTask(@RequestBody TaskEntity taskEntity){
        return ResponseEntity.ok(taskService.saveTask(taskEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Long id, TaskEntity taskEntity){
        return ResponseEntity.ok(taskService.updateTask(id, taskEntity));
    }

    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<TaskEntity> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId){
        return ResponseEntity.ok(taskService.assignTaskToUser(taskId, userId));
    }

    @GetMapping("/priority-page/{taskPriority}")
    public ResponseEntity<Page<TaskEntity>> findByPriorityAndGroupId(TaskPriority taskPriority,
                                                                     @PathVariable Long groupId,
                                                                     @RequestParam(defaultValue = "0") int page,
                                                                     @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        return ResponseEntity.ok(taskService.findByPriorityAndGroupId(taskPriority, groupId, pageable));
    }

    @GetMapping("/status-page/{taskStatus}")
    public ResponseEntity<Page<TaskEntity>> findByStatusAndGroupId(TaskStatus taskStatus,
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
