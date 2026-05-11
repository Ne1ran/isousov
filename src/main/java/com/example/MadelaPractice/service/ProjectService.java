package com.example.MadelaPractice.service;

import com.example.MadelaPractice.entity.OrganizationEntity;
import com.example.MadelaPractice.entity.ProjectEntity;
import com.example.MadelaPractice.entity.UserEntity;
import com.example.MadelaPractice.exception.EntityAlreadyExistsException;
import com.example.MadelaPractice.exception.EntityDoesNotExistException;
import com.example.MadelaPractice.model.IdListModel;
import com.example.MadelaPractice.model.ProjectSaveModel;
import com.example.MadelaPractice.model.TargetOrganizationModel;
import com.example.MadelaPractice.repository.OrganizationRepo;
import com.example.MadelaPractice.repository.ProjectRepo;
import com.example.MadelaPractice.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProjectService {

    private static final Logger log = LoggerFactory.getLogger(ProjectService.class);

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private UserRepo userRepo;

    public List<ProjectEntity> listByOrganization(Long organizationId) {
        log.debug("Listing projects for organization {}", organizationId);
        return new ArrayList<>(projectRepo.findByOrganization_Id(organizationId));
    }

    @Transactional
    public ProjectEntity create(ProjectSaveModel model) {
        log.info("Creating project code={} for organization {}", model.getCode(), model.getOrganizationId());
        if (projectRepo.existsByOrganization_IdAndCode(model.getOrganizationId(), model.getCode())) {
            throw new EntityAlreadyExistsException("Project code already exists in this organization");
        }
        OrganizationEntity org = organizationRepo.findById(model.getOrganizationId())
                .orElseThrow(() -> new EntityDoesNotExistException("Organization not found"));
        ProjectEntity p = new ProjectEntity();
        p.setCode(model.getCode());
        p.setName(model.getName());
        p.setStartDate(model.getStartDate());
        p.setDeadline(model.getDeadline());
        p.setPriority(model.getPriority());
        org.addProject(p);
        ProjectEntity saved = projectRepo.save(p);
        log.info("Project id={} persisted", saved.getId());
        return saved;
    }

    @Transactional
    public ProjectEntity replaceAssignees(Long projectId, IdListModel body) {
        log.info("Replacing assignees for project id={}, user count={}", projectId, body.getIds().size());
        ProjectEntity project = projectRepo.findById(projectId)
                .orElseThrow(() -> new EntityDoesNotExistException("Project not found"));
        project.clearAssignees();
        Set<UserEntity> users = new HashSet<>();
        for (Long uid : body.getIds()) {
            UserEntity u = userRepo.findById(uid)
                    .orElseThrow(() -> new EntityDoesNotExistException("User not found: " + uid));
            users.add(u);
        }
        for (UserEntity u : users) {
            project.addAssignee(u);
        }
        ProjectEntity saved = projectRepo.save(project);
        log.info("Project id={} assignees updated", saved.getId());
        return saved;
    }

    @Transactional
    public ProjectEntity moveToOrganization(Long projectId, TargetOrganizationModel body) {
        log.info("Moving project id={} to organization {}", projectId, body.getTargetOrganizationId());
        ProjectEntity project = projectRepo.findById(projectId)
                .orElseThrow(() -> new EntityDoesNotExistException("Project not found"));
        OrganizationEntity target = organizationRepo.findById(body.getTargetOrganizationId())
                .orElseThrow(() -> new EntityDoesNotExistException("Target organization not found"));
        OrganizationEntity current = project.getOrganization();
        if (current != null) {
            current.removeProject(project);
        }
        target.addProject(project);
        ProjectEntity saved = projectRepo.save(project);
        log.info("Project id={} moved to organization id={}", saved.getId(), target.getId());
        return saved;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting project id={}", id);
        ProjectEntity p = projectRepo.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Project not found"));
        p.clearAssignees();
        OrganizationEntity org = p.getOrganization();
        if (org != null) {
            org.removeProject(p);
            organizationRepo.save(org);
        } else {
            projectRepo.delete(p);
        }
    }
}
