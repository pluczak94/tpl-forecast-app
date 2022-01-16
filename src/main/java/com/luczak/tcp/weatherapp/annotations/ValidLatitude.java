package com.luczak.tcp.weatherapp.annotations;

import com.luczak.tcp.weatherapp.validators.LatitudeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = LatitudeValidator.class)
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidLatitude {
    String message() default "Invalid latitude";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}