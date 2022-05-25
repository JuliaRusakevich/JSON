package com.gmail.juliarusakevich.service.validator;

import com.gmail.juliarusakevich.core.dto.group.CreateGroupDto;
import com.gmail.juliarusakevich.service.validator.api.IValidator;

import java.util.regex.Pattern;

public class CreateGroupValidator implements IValidator<CreateGroupDto> {

    private static final CreateGroupValidator INSTANCE = new CreateGroupValidator();

    private static final String REGEX = "^[[а-яА-Яa-zA-Z0-9\\-\\s]]{2,35}$";

    private CreateGroupValidator() {
    }

    @Override
    public ValidationResult isValid(CreateGroupDto dto) {
        var result = new ValidationResult();
        if (!isValidName(REGEX, dto.getGroupName())) {
            result.add(Error.of("invalid.group_name: " + dto.getGroupName(),
                    "There was used incorrect symbols or group name is long"));
        }
        return result;
    }

    public boolean isValidName(String regex, String input) {
        return Pattern.matches(regex, input);
    }

    public static CreateGroupValidator getInstance() {
        return INSTANCE;
    }
}
