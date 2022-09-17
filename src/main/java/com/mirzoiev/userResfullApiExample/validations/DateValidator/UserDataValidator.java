package com.mirzoiev.userResfullApiExample.validations.DateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UserDataValidatorImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserDataValidator {
//    int value();
    String message() default "{UserDataValidator}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
