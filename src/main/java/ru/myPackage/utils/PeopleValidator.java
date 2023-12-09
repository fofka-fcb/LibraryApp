package ru.myPackage.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.myPackage.dao.PeopleDAO;
import ru.myPackage.models.People;

@Component
public class PeopleValidator implements Validator {

    private final PeopleDAO peopleDAO;

    @Autowired
    public PeopleValidator(PeopleDAO peopleDAO) {
        this.peopleDAO = peopleDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return People.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        People people = (People) target;

        if (peopleDAO.showByFullName(people.getFullName()) != null) {
            errors.rejectValue("fullName", "", "This name is already taken");
        }
    }
}
