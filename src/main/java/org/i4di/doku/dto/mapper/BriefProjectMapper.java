package org.i4di.doku.dto.mapper;

import org.i4di.doku.domain.Project;
import org.i4di.doku.dto.BriefProjectDTO;
import org.i4di.doku.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BriefProjectMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Project.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Project.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "users", target = "users")
    BriefProjectDTO projectToProjectDTO(Project project);

    List<BriefProjectDTO> projectsToProjectDTOs(List<Project> projects);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = Project.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "updatedAt", target = "updatedAt", dateFormat = Project.ISO_8601_TIMESTAMP_FORMAT)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "users", target = "users")
    Project projectDTOToProject(BriefProjectDTO projectDTO);

    List<Project> projectDTOsToProjects(List<BriefProjectDTO> projectDTOs);
}
