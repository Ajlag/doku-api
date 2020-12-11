package org.i4di.doku.dto;

import com.sun.istack.NotNull;
import org.i4di.common.dto.TimestampedDTO;
import org.i4di.common.validator.Required;
import org.i4di.common.validator.document.UniqueTitle;

import javax.validation.constraints.Positive;

public class DocumentDTO extends TimestampedDTO {

    @Required
    // @UniqueTitle eventualno da proverava unutar jednog projekta (neki nacin da
    // se prosledi id projekta i po tome provera
    private String title;

    @Required
    private String content;

    @Positive
    private Long orderNumber;

    private String uuid;

    private Boolean deleted;

    @Required
    private String status;

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
        if (deleted == null) {
            deleted = false;
        }
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

}
