package com.ast.tech_al_api.services;

import com.ast.tech_al_api.entities.GroupEntity;

import java.util.List;

public interface GroupService {
    List<GroupEntity> getAllGroups();
    GroupEntity getGroupById(Long id);
    GroupEntity saveGroup(GroupEntity group);
    GroupEntity updateGroup(Long id, GroupEntity group);
    void deleteGroup(Long id);
}
