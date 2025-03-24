package com.ast.tech_al_api.services;

import com.ast.tech_al_api.entities.Organization;

import java.util.List;

public interface OrganizationService {
    List<Organization> getAllOrganizations();
    Organization getOrganizationById(Long id);
    Organization saveOrganization(Organization organization);
    Organization updateOrganization(Long id, Organization organization);
    void deleteOrganization(Long id);
}
