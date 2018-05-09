package ua.phone.book.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BaseValidator implements Validator {

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "empty.field");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }


}
