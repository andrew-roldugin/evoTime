package ru.vsu.csf.g7.validations.annotations;


import ru.vsu.csf.g7.validations.validators.PasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "Пароли не совпадают";

    Class<?>[] groups() default{};

    Class<? extends Payload>[] payload() default {};
}
