package ua.phone.book.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.phone.book.models.Employee;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName", "empty.field");
        if (target instanceof Employee) {
            Employee employee = (Employee) target;
            if (employee.getPositionId() == 0) {
                errors.rejectValue("positionId", "employee.position.not.selected");
            }
            if (employee.getDepartmentId() == 0) {
                errors.rejectValue("departmentId", "employee.department.not.selected");
            }
            validateLedlineNum(employee.getLedlineNumber().trim(), errors);
            validateMobileNum(employee.getMobileNumber().trim(), errors);

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
            if (number.length() < 10 || number.length() > 10) {
                errors.rejectValue("mobileNumber", "employee.mobile.number.min.size");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("mobileNumber", "employee.number.incorrect");
        }
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }


}
