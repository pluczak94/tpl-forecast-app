package com.luczak.tcp.weatherapp.validators;

import com.luczak.tcp.weatherapp.annotations.ValidLongitude;
import org.apache.commons.lang3.Range;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LongitudeValidator implements ConstraintValidator<ValidLongitude, Double> {

    private final static Range<Double> VALID_LONGITUDE = Range.between(-180d, 180d);

    @Override
    public boolean isValid(Double longitude, ConstraintValidatorContext constraintValidatorContext) {
        return VALID_LONGITUDE.contains(longitude);
    }
}
