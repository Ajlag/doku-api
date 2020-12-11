package org.i4di.doku.dto;

import org.i4di.common.dto.TimestampedDTO;
import org.i4di.common.validator.Required;
import org.i4di.common.validator.project.UniqueName;

import java.util.HashSet;
import java.util.Set;

public class ProjectDTO extends TimestampedDTO {

    @Required
    private String name;

    @Required
    private String description;

    private Boolean deleted;

    private Set<UserDTO> users;

    private Set<DocumentDTO> documents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        if (deleted == null) {
            deleted = false;
        }
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }

    public Set<DocumentDTO> getDocuments() {
        if (documents == null) {
            documents = new HashSet<>();
        }
        return documents;
    }

    public void setDocuments(Set<DocumentDTO> documents) {
        this.documents = documents;
    }
}
