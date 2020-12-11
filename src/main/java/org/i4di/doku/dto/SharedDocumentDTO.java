package org.i4di.doku.dto;

import org.i4di.common.dto.TimestampedDTO;

public class SharedDocumentDTO extends TimestampedDTO {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
