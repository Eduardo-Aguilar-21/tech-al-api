package com.ast.tech_al_api.repositories;

import com.ast.tech_al_api.entities.UserEntity;
import com.ast.tech_al_api.enums.UserRole;
import com.ast.tech_al_api.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    UserEntity findByEmail(String email);
    List<UserEntity> findByOrganizationId(Long organizationId);
    List<UserEntity> findByGroupId(Long groupId);
    List<UserEntity> findByRole(UserRole role);
    List<UserEntity> findByStatus(UserStatus status);
}
