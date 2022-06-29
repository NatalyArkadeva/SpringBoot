package task5.exception;

public class PersonException extends RuntimeException {
    private ExceptionMessage message;

    public PersonException(ExceptionMessage message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.getMessage();
    }
}
