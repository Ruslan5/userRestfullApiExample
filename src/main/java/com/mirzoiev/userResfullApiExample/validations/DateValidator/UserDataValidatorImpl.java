package com.mirzoiev.userResfullApiExample.validations.DateValidator;

import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class UserDataValidatorImpl implements ConstraintValidator<UserDataValidator, Date> {
    @Value("${limit.age}")
    private int limitAge;

    @Override
    public void initialize(UserDataValidator userDataValidator) {
    }

    @Override
    public boolean isValid(Date registrationDate, ConstraintValidatorContext constraintValidatorContext) {
        Date currentDate = new Date(System.currentTimeMillis());
        Calendar a = getCalendar(registrationDate);
        Calendar b = getCalendar(currentDate);
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        return limitAge < diff;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
}
