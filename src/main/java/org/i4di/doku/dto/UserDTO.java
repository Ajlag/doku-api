package org.i4di.doku.dto;

import org.i4di.common.dto.TimestampedDTO;


import java.util.HashSet;
import java.util.Set;

public class UserDTO extends TimestampedDTO {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean deleted;
    private Boolean active;
    private Set<RoleDTO> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Boolean getActive() {
        if (active == null) {
            active = false;
        }
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<RoleDTO> getRoles() {
        if (roles == null) {
            roles = new HashSet<>();
        }
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
