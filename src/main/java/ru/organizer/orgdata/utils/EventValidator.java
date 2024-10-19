package ru.organizer.orgdata.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.organizer.orgdata.models.EventAddApplication;
@Component
public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return EventAddApplication.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventAddApplication eventAddApplication = (EventAddApplication)target;
//        String snils = String.valueOf(eventAddApplication.getSnilsOrganizer());
//        boolean matchesSnils = snils.matches("^\\d{11}");
//        if(!matchesSnils){
//            errors.rejectValue("snilsOrganizer", "", "СНИЛС должен содержать 11 цифр");
//        }
        if(eventAddApplication.getParticipantsFrom() > eventAddApplication.getParticipantsTo()){
            errors.rejectValue("participantsFrom", "", "значение поля 'От' должно быть не больше значения поля 'До'");
        }
    }
}
