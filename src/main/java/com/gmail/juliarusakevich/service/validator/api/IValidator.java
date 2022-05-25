package com.gmail.juliarusakevich.service.validator.api;

import com.gmail.juliarusakevich.service.validator.ValidationResult;

public interface IValidator<T> {

    ValidationResult isValid(T dto);

}
