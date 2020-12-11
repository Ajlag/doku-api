package org.i4di.common.validator.permission;

import org.i4di.doku.service.PermissionService;
import org.i4di.doku.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private PermissionService permissionService;

    @Autowired
    public UniqueNameValidator(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public void initialize(UniqueName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !permissionService.show(value).isPresent();
    }
}
