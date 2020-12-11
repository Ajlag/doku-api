package org.i4di.common.validator.user;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmailFormatValidator implements ConstraintValidator<ValidEmailFormat, String> {
    @Override
    public void initialize(ValidEmailFormat constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (value.matches("^(.+)@(.+)$"))
//            return true;
//        return false;

//        EmailValidator validator = EmailValidator.getInstance();
//
//        return validator.isValid(value);

        return value.matches("^(.+)@(\\S+)$");
    }
}
