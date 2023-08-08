package com.eds.technicalchallenge.annotations.xss;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Pattern;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConstraintNotXss.class)
public @interface NotXss {
    String message() default "Type can only contain alphanumeric characters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
