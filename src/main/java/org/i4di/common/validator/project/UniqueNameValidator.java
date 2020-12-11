package org.i4di.common.validator.project;

import org.i4di.doku.service.ProjectService;
import org.i4di.doku.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private ProjectService projectService;

    @Autowired
    public UniqueNameValidator(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public void initialize(UniqueName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !projectService.show(value).isPresent();
    }
}
