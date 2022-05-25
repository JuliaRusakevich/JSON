package com.gmail.juliarusakevich.service.validator;

import com.gmail.juliarusakevich.core.dto.student.CreateStudentDto;
import com.gmail.juliarusakevich.service.validator.api.IValidator;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class CreateStudentValidator implements IValidator<CreateStudentDto> {

    private static final CreateStudentValidator INSTANCE = new CreateStudentValidator();

    private static final String REGEX = "^[а-яА-Я]{3,10}$";
    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 17;
    private static final double MIN_SCORE = 0.0;
    private static final double MAX_SCORE = 10.0;
    private static final String TARGET_COMMA = ",";
    private static final String REPLACEMENT_DOT = ".";
    private static final String PATTERN_FOR_SCORE = "#.#";

    private CreateStudentValidator() {
    }

    @Override
    public ValidationResult isValid(CreateStudentDto dto) {
        var result = new ValidationResult();
        if (!isValidName(REGEX, dto.getName())) {
            result.add(Error.of(
                    "invalid.name: " + dto.getName(),
                    "Valid field \"name\": from 3 to 10 russian symbols")
            );
        }

        if (dto.getAge() < MIN_AGE || dto.getAge() > MAX_AGE) {
            result.add(Error.of(
                    "invalid.age: " + dto.getAge(),
                    "Valid field \"age\": from 7 to 17 years"
            ));
        }

        var score = dto.getScore();
        var formattedSCore = getFormattedDouble(score);
        if (!isValidScore(formattedSCore)) {
            result.add(Error.of(
                    "invalid.score: " + score,
                    "Valid field \"score\": from 0.0 to 10.0 years"
            ));
        }


        /*
        сделать нормальную валидацию для boolean
                     result.add(Error.of(
                    "invalid.olympicGamer",
                    "Valid field \"olympic_gamer\": true/false"));
         */

        return result;
    }

    public boolean isValidName(String regex, String input) {
        return Pattern.matches(regex, input);
    }

    private static String getFormattedDouble(double nonFormattedDouble) {
        return new DecimalFormat(PATTERN_FOR_SCORE)
                .format(nonFormattedDouble)
                .replace(TARGET_COMMA, REPLACEMENT_DOT);

    }

    public boolean isValidScore(String result) {
        return Double.parseDouble(result) > MIN_SCORE && Double.parseDouble(result) < MAX_SCORE;
    }

    public static CreateStudentValidator getInstance() {
        return INSTANCE;
    }
}
