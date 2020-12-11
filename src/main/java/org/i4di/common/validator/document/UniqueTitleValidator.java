package org.i4di.common.validator.document;

import org.i4di.doku.service.DocumentService;
import org.i4di.doku.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueTitleValidator implements ConstraintValidator<UniqueTitle, String> {

    private DocumentService documentService;

    @Autowired
    public UniqueTitleValidator(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Override
    public void initialize(UniqueTitle constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !documentService.show(value).isPresent();
    }
}
