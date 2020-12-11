package org.i4di.doku.domain;

import org.hibernate.envers.Audited;
import org.i4di.common.domain.Timestamped;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import javax.persistence.*;


@Entity
@Audited
public class Document extends Timestamped {

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "text")
    private String content;

    @Column(nullable = false)
    private Long orderNumber;

    @Column(nullable = false)
    private String uuid;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean deleted;

    @Column(nullable = false)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
