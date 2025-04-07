package com.ast.tech_al_api.services.impl;

import com.ast.tech_al_api.entities.GroupEntity;
import com.ast.tech_al_api.repositories.GroupRepository;
import com.ast.tech_al_api.services.GroupService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public List<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public GroupEntity getGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + id));
    }

    @Override
    public List<GroupEntity> findByOrganizationId(Long organizationId) {
        return groupRepository.findByOrganizationId(organizationId);
    }

    @Override
    public Page<GroupEntity> findByOrganizationId(Long organizationId, Pageable pageable) {
        return groupRepository.findByOrganizationId(organizationId, pageable);
    }

    @Override
    public GroupEntity saveGroup(GroupEntity group) {
        return groupRepository.save(group);
    }

    @Override
    public GroupEntity updateGroup(Long id, GroupEntity group) {
        GroupEntity existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found with id: " + id));

        existingGroup.setName(group.getName());
        return groupRepository.save(existingGroup);
    }

    @Override
    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new EntityNotFoundException("Group not found with id: " + id);
        }
        groupRepository.deleteById(id);
    }
}
