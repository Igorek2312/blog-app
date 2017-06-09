package com.github.igorek2312.blog.app.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Igor Rybak
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PasswordsEqualConstraintValidator.class)
public @interface ConfirmPasswordConstraint {
    String message() default "{ConfirmPasswordConstraint}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
