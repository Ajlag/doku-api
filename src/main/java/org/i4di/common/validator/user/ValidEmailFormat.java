package org.i4di.common.validator.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEmailFormatValidator.class)
public @interface ValidEmailFormat {

    String message() default "Invalid email address format!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
