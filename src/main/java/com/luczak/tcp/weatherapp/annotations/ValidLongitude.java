package com.luczak.tcp.weatherapp.annotations;

import com.luczak.tcp.weatherapp.validators.LongitudeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LongitudeValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLongitude {
    String message() default "Invalid longitude";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}