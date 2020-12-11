package org.i4di.doku.service;

import org.i4di.doku.dto.BriefProjectDTO;
import org.i4di.doku.dto.ProjectDTO;

import java.util.List;
import java.util.Optional;

public interface ProjectService {

    List<BriefProjectDTO> listAll();

    Optional<ProjectDTO> show(Long id);

    Optional<ProjectDTO> show(String name);

    Optional<ProjectDTO> create(ProjectDTO project);

    Optional<ProjectDTO> update(ProjectDTO project);

    boolean delete(Long id);

    boolean addUser(Long projectId, Long authorId);

    boolean removeUser(Long projectId, Long authorId);
}
