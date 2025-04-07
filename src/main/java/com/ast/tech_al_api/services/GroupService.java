package com.ast.tech_al_api.services;

import com.ast.tech_al_api.entities.GroupEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupService {
    List<GroupEntity> getAllGroups();
    GroupEntity getGroupById(Long id);
    List<GroupEntity> findByOrganizationId(Long organizationId);
    Page<GroupEntity> findByOrganizationId(Long organizationId, Pageable pageable);
    GroupEntity saveGroup(GroupEntity group);
    GroupEntity updateGroup(Long id, GroupEntity group);
    void deleteGroup(Long id);
}
