package org.i4di.doku.service.impl;

import org.i4di.doku.domain.Project;
import org.i4di.doku.domain.User;
import org.i4di.doku.dto.BriefProjectDTO;
import org.i4di.doku.dto.ProjectDTO;
import org.i4di.doku.dto.mapper.BriefProjectMapper;
import org.i4di.doku.dto.mapper.ProjectMapper;
import org.i4di.doku.repository.ProjectRepository;
import org.i4di.doku.repository.UserRepository;
import org.i4di.doku.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private ProjectMapper projectMapper;
    private BriefProjectMapper briefProjectMapper;

    private UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, BriefProjectMapper briefProjectMapper, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.briefProjectMapper = briefProjectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<BriefProjectDTO> listAll() {
        return briefProjectMapper.projectsToProjectDTOs(
            projectRepository.findByDeleted(false)
        );
    }

    @Override
    public Optional<ProjectDTO> show(Long id) {
        Optional<Project> byId = projectRepository.findById(id);

        return byId.map(project -> projectMapper.projectToProjectDTO(project));
    }

    @Override
    public Optional<ProjectDTO> show(String name) {
        Optional<Project> byName = projectRepository.findByName(name);

        return byName.map(project -> projectMapper.projectToProjectDTO(project));
    }

    @Override
    public Optional<ProjectDTO> create(ProjectDTO project) {
        Project toCreate = projectMapper.projectDTOToProject(project);

        return Optional.of(projectMapper.projectToProjectDTO(
            projectRepository.save(toCreate)
        ));
    }

    @Override
    public Optional<ProjectDTO> update(ProjectDTO project) {
        Optional<Project> byId = projectRepository.findById(project.getId());

        if (!byId.isPresent()) {
            return Optional.empty();
        }

        Project updated = projectMapper.projectDTOToProject(project);

        return Optional.of(projectMapper.projectToProjectDTO(
            projectRepository.save(updated)
        ));
    }

    @Override
    public boolean delete(Long id) {
        Optional<Project> byId = projectRepository.findById(id);

        if (!byId.isPresent()) {
            return false;
        }

        projectRepository.delete(id);

        return true;
    }

    @Override
    public boolean addUser(Long projectId, Long authorId) {
        Optional<Project> projectById = projectRepository.findById(projectId);
        Optional<User> userById = userRepository.findById(authorId);

        if (!projectById.isPresent() || !userById.isPresent()) {
            return false;
        }

        if (projectRepository.countProjectsUsers(projectId, authorId) > 0) {
            return false;
        }

        projectRepository.addUser(projectId, authorId);

        return true;
    }

    @Override
    public boolean removeUser(Long projectId, Long authorId) {
        Optional<Project> projectById = projectRepository.findById(projectId);
        Optional<User> userById = userRepository.findById(authorId);

        if (!projectById.isPresent() || !userById.isPresent()) {
            return false;
        }
        if (projectRepository.countProjectsUsers(projectId, authorId) == 0) {
            return false;
        }
        projectRepository.removeUser(projectId, authorId);
        return true;

    }


}
