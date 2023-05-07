package ru.vsu.csf.g7.validations.validators;

import ru.vsu.csf.g7.payload.request.SignupRequest;
import ru.vsu.csf.g7.validations.annotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignupRequest> {

    @Override
    public boolean isValid(SignupRequest request, ConstraintValidatorContext constraintValidatorContext) {
        return request.getPassword().equals(request.getConfirmPassword());
    }
}
