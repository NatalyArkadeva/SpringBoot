package task5.exception;

public class EntityException extends RuntimeException {
    private ExceptionMessage message;

    public EntityException(ExceptionMessage message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message.getMessage();
    }
}
