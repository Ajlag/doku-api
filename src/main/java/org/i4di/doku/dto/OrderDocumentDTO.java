package org.i4di.doku.dto;

import org.i4di.common.validator.Required;

import javax.validation.constraints.Positive;
import java.io.Serializable;

public class OrderDocumentDTO implements Serializable {

    @Positive
    private Long orderNumber;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }
}
