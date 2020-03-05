package ua.example.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.example.dao.UserDAO;
import ua.example.models.User;

@Component
public class UserValidator implements Validator {

    private final UserDAO userDAO;

    public UserValidator(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userDAO.getOne(user.getEmail()) != null) {
            errors.rejectValue("email", "", "This email is already use");
        }
    }
}
