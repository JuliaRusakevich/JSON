package com.gmail.juliarusakevich.service.validator;


import java.util.List;

public class ValidationException extends IllegalArgumentException {

    private final List<Error> errors;

    public List<Error> getErrors() {
        return errors;
    }

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }
}
