package org.i4di.account.dto;

import org.i4di.common.dto.TimestampedDTO;
import org.i4di.doku.domain.Category;
import org.joda.time.DateTime;

public class TokenDTO extends TimestampedDTO {

    private Category category;
    private String value;
    private DateTime expireTime;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(DateTime expireTime) {
        this.expireTime = expireTime;
    }
}
