package com.gmail.juliarusakevich.service.validator;

import java.util.Objects;

public class Error {
    private  final String code;
    private final String message;

    private Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Error of(String code, String message) {
        return new Error(code, message);
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Error)) return false;
        Error error = (Error) o;
        return Objects.equals(getCode(), error.getCode()) && Objects.equals(getMessage(), error.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getMessage());
    }

    @Override
    public String toString() {
        return "Error{" +
               "code='" + code + '\'' +
               ", message='" + message + '\'' +
               '}';
    }
}
