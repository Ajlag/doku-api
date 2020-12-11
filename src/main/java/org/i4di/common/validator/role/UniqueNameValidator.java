package org.i4di.common.validator.role;

import org.i4di.doku.service.RoleService;
import org.i4di.doku.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, String> {

    private RoleService roleService;

    @Autowired
    public UniqueNameValidator(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void initialize(UniqueName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !roleService.show(value).isPresent();
    }
}
