package org.i4di.doku.dto;

import org.i4di.common.dto.TimestampedDTO;
import org.i4di.common.validator.Required;
import org.i4di.common.validator.permission.UniqueName;

public class PermissionDTO extends TimestampedDTO {

    @Required
    @UniqueName
    private String name;

    @Required
    private String description;

    private Boolean deleted;

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
}
