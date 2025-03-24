package com.ast.tech_al_api.services;

import com.ast.tech_al_api.entities.UserEntity;
import com.ast.tech_al_api.enums.UserRole;
import com.ast.tech_al_api.enums.UserStatus;

import java.util.List;

public interface UserService {
    List<UserEntity> getUsers();
    UserEntity getUser(Long id);
    UserEntity getUserByUsername(String username);
    UserEntity getUserByEmail(String email);
    List<UserEntity> getUsersByOrganization(Long organizationId);
    List<UserEntity> getUsersByGroup(Long groupId);
    List<UserEntity> getUsersByRole(UserRole role);
    List<UserEntity> getUsersByStatus(UserStatus status);
    UserEntity saveUser(UserEntity user);
    UserEntity updateUser(Long id, UserEntity user);
    void deleteUser(Long id);





}
