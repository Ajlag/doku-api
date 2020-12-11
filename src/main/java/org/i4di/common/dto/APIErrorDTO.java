package org.i4di.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class APIErrorDTO implements Serializable {
    private List<FieldErrorDTO> errors;

    public APIErrorDTO() {
        this.errors = new ArrayList<>();
    }

    public void addFieldError(String field, String message) {
        errors.add(new FieldErrorDTO(field, message));
    }

    public List<FieldErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorDTO> errors) {
        this.errors = errors;
    }

    public boolean isEmpty() {
        return errors.isEmpty();
    }
}
