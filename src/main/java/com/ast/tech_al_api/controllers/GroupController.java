package com.ast.tech_al_api.controllers;

import com.ast.tech_al_api.entities.GroupEntity;
import com.ast.tech_al_api.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupEntity>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupEntity> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<List<GroupEntity>> getGroupsByOrganizationId(@PathVariable Long organizationId) {
        return ResponseEntity.ok(groupService.findByOrganizationId(organizationId));
    }

    @GetMapping("/organization-page/{organizationId}")
    public ResponseEntity<Page<GroupEntity>> getGroupsByOrganizationId(@PathVariable Long organizationId,
                                                                       @RequestParam(defaultValue = "0") int page,
                                                                       @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(groupService.findByOrganizationId(organizationId, pageable));
    }

    @PostMapping
    public ResponseEntity<GroupEntity> saveGroup(@RequestBody GroupEntity group){
        return ResponseEntity.ok(groupService.saveGroup(group));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupEntity> updateGroup(@PathVariable Long id, @RequestBody GroupEntity group){
        return ResponseEntity.ok(groupService.updateGroup(id, group));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id){
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}
