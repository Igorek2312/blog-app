package com.github.igorek2312.blog.app.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Igor Rybak
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {
    String message() default "{Unique.user.email}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
