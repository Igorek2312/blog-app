package com.github.igorek2312.blog.app.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Igor Rybak
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Documented
public @interface UniqueUsername {
    String message() default "{Unique.user.username}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
