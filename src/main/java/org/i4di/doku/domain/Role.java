package org.i4di.doku.domain;

import org.hibernate.envers.Audited;
import org.i4di.common.domain.Timestamped;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
public class Role extends Timestamped {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted;

    @ManyToMany
    @JoinTable(
        name = "roles_permissions",
        joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    Set<Permission> permissions;

    public Role() {
        permissions = new HashSet<>();
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
