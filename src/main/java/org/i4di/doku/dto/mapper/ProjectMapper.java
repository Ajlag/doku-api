package org.i4di.doku.dto.mapper;

import org.i4di.doku.domain.Project;
import org.i4di.doku.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DocumentMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Project.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Project.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "users", target = "users")
    @Mapping(source = "documents", target = "documents")
    ProjectDTO projectToProjectDTO(Project project);

    List<ProjectDTO> projectsToProjectDTOs(List<Project> projects);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Project.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Project.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "deleted", target = "deleted")
    @Mapping(source = "users", target = "users")
    @Mapping(source = "documents", target = "documents")
    Project projectDTOToProject(ProjectDTO projectDTO);

    List<Project> projectDTOsToProjects(List<ProjectDTO> projectDTOs);
}
