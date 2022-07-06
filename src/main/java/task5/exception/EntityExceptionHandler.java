package task5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EntityIncorrectData> handleException(EntityException exception) {
        EntityIncorrectData data = new EntityIncorrectData();
        data.setMessage(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityIncorrectData> handleException(Exception exception) {
        EntityIncorrectData data = new EntityIncorrectData();
        data.setMessage(ExceptionMessage.INCORRECT_DATA.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}
