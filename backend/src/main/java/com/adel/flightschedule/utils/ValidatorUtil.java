package com.adel.flightschedule.utils;

import org.springframework.util.ObjectUtils;

public class ValidatorUtil {

    private String label;
    private Object value;

    public static ValidatorUtil builder() {
        return new ValidatorUtil();
    }

    public ValidatorUtil param(final String label, final Object value) {
        this.label = label;
        this.value = value;
        return this;
    }

    public ValidatorUtil notNull() {
        if (ObjectUtils.isEmpty(value)) {
            throw new IllegalArgumentException(label + " cannot be null");
        }
        return this;
    }

}
