package org.i4di.doku.dto;

import org.i4di.common.dto.TimestampedDTO;
import org.i4di.common.validator.Required;
import org.i4di.common.validator.role.UniqueName;


import java.util.HashSet;
import java.util.Set;

public class RoleDTO extends TimestampedDTO {

    @Required
    @UniqueName
    private String name;

    private Boolean deleted;

    private Set<PermissionDTO> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<PermissionDTO> getPermissions() {
        if (permissions == null) {
            permissions = new HashSet<>();
        }
        return permissions;
    }

    public void setPermissions(Set<PermissionDTO> permissions) {
        this.permissions = permissions;
    }

}
