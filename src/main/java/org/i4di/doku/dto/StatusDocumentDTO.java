package org.i4di.doku.dto;

import org.i4di.common.validator.Required;

import java.io.Serializable;

public class StatusDocumentDTO implements Serializable {

    @Required
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
