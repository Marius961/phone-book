package ua.phone.book.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.phone.book.models.Emploee;

@Component
public class EmploeeValidator implements Validator {

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "empty.field");
        if (target instanceof Emploee) {
            Emploee emploee = (Emploee) target;
            if (emploee.getPositionId() == 0) {
                errors.rejectValue("positionId", "emploee.position.not.selected");
            }
            if (emploee.getDepartmentId() == 0) {
                errors.rejectValue("departmentId", "emploee.department.not.selected");
            }
            validateLedlineNum(emploee.getLedlineNumber().trim(), errors);
            validateMobileNum(emploee.getMobileNumber().trim(), errors);

        }
    }

    private void validateLedlineNum(String number, Errors errors) {
        try {
            int test = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            errors.rejectValue("ledlineNumber", "emoloee.number.incorrect");
        }
    }

    private void validateMobileNum(String number, Errors errors) {
        try {
            int test = Integer.parseInt(number);
            if (number.length() != 10) {
                errors.rejectValue("mobileNumber", "emploee.mobile.number.min.size");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("mobileNumber", "emoloee.number.incorrect");
        }
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }


}
