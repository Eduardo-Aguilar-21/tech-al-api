package com.ast.tech_al_api.repositories;

import com.ast.tech_al_api.entities.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    List<GroupEntity>  findByOrganizationId(Long organizationId);
    Page<GroupEntity> findByOrganizationId(Long organizationId, Pageable pageable);
}
