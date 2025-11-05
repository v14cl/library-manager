package com.librarymanager;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern phonePattern = Pattern.compile("^\\d{10}$");

    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
    }

    public static void validatePhone(String phone) {
        if (!phonePattern.matcher(phone).matches()) {
            throw new IllegalArgumentException("Invalid phone format: " + phone);
        }
    }

    public static void validateId(int id, String fieldName) {
        if (id <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

    public static void validateNotNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }
}

