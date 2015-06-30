package com.java.error;

public final class FieldErrorDTO {

    private final String field;

    private final String message;

    FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }


}
