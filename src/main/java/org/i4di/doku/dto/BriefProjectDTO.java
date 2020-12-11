package org.i4di.doku.dto;

import org.i4di.common.dto.TimestampedDTO;
import org.i4di.common.validator.Required;

import java.util.Set;

public class BriefProjectDTO extends TimestampedDTO {

    private String name;

    private String description;

    private Set<UserDTO> users;

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

    public Set<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(Set<UserDTO> users) {
        this.users = users;
    }
}
