package com.luczak.tcp.weatherapp.validators;

import com.luczak.tcp.weatherapp.annotations.ValidLatitude;
import org.apache.commons.lang3.Range;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LatitudeValidator implements ConstraintValidator<ValidLatitude, Double> {

    private final static Range<Double> VALID_LATITUDE = Range.between(-90d, 90d);

    @Override
    public boolean isValid(Double latitude, ConstraintValidatorContext constraintValidatorContext) {
        return VALID_LATITUDE.contains(latitude);
    }
}
