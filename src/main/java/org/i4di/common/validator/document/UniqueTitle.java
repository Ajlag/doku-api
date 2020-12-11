package org.i4di.common.validator.document;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueTitleValidator.class)
public @interface UniqueTitle {

    String message() default "Document with this title already exists!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
