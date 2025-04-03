package com.ast.tech_al_api.controllers;

import com.ast.tech_al_api.entities.UserEntity;
import com.ast.tech_al_api.enums.UserRole;
import com.ast.tech_al_api.enums.UserStatus;
import com.ast.tech_al_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserEntity>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/by-username")
    public ResponseEntity<UserEntity> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("/by-email")
    public ResponseEntity<UserEntity> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/by-organization")
    public ResponseEntity<List<UserEntity>> getUsersByOrganization(@RequestParam Long organizationId) {
        return ResponseEntity.ok(userService.getUsersByOrganization(organizationId));
    }

    @GetMapping("/by-group")
    public ResponseEntity<List<UserEntity>> getUsersByGroup(@RequestParam Long groupId) {
        return ResponseEntity.ok(userService.getUsersByGroup(groupId));
    }

    @GetMapping("/by-role")
    public ResponseEntity<List<UserEntity>> getUsersByRole(@RequestParam UserRole role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<UserEntity>> getUsersByStatus(@RequestParam UserStatus status) {
        return ResponseEntity.ok(userService.getUsersByStatus(status));
    }

    @PostMapping
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user){
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id,@RequestBody UserEntity user){
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
