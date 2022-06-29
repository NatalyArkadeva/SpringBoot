package task5.util;

import org.springframework.stereotype.Component;
import task5.exception.ExceptionMessage;
import task5.exception.PersonException;

@Component
public class Util {
    public void throwExceptionIfNameIncorrect(String name){
        if (!name.matches("[a-zA-Z]*")) {
            throw new PersonException(ExceptionMessage.INCORRECT_DATA);
        }
    }
}
