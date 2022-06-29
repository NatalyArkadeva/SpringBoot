package task5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<PersonIncorrectData> handleException(PersonException exception) {
        PersonIncorrectData data = new PersonIncorrectData();
        data.setMessage(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<PersonIncorrectData> handleException(Exception exception) {
        PersonIncorrectData data = new PersonIncorrectData();
        data.setMessage(ExceptionMessage.INCORRECT_DATA.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
