package com.eds.technicalchallenge.annotations.xss;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConstraintNotXss implements ConstraintValidator<NotXss, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        var regexp = "[a-zA-Z0-9 ]+";
        Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(s);
        return m.matches();
    }
}
