package org.i4di.doku.domain;

import org.hibernate.envers.Audited;
import org.i4di.common.domain.Timestamped;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
public class Project extends Timestamped {

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted;

    @ManyToMany
    @JoinTable(
        name = "projects_users",
        joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    Set<User> users;

    @OneToMany(mappedBy = "project")
    private Set<Document> documents;

    public Project() {
        users = new HashSet<>();
        documents = new HashSet<>();
    }

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
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
}
