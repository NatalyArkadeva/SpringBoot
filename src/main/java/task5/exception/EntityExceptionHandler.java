package task5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EntityExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EntityIncorrectData> handleException(EntityException exception) {
        EntityIncorrectData data = new EntityIncorrectData();
        data.setMessage(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler
//    public ResponseEntity<EntityIncorrectData> handleException(Exception exception) {
//        EntityIncorrectData data = new EntityIncorrectData();
//        data.setMessage(ExceptionMessage.INCORRECT_DATA.getMessage());
//        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            errors.put(fieldName, error.getDefaultMessage());
//            if(fieldName.equals("mobile")){
//                errors.put(fieldName, ExceptionMessage.INCORRECT_MOBILE.getMessage());
//            } else if(fieldName.equals("password")){
//                errors.put(fieldName, ExceptionMessage.INCORRECT_PASSWORD.getMessage());
//            } else if(fieldName.equals("birthday")){
//                errors.put(fieldName, ExceptionMessage.INCORRECT_BIRTHDAY.getMessage());
//            } else errors.put(fieldName, error.getDefaultMessage());
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
