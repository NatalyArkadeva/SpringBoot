package task5.exception;

public enum ExceptionMessage {
    NOT_FOUND("Пользователь не найден"), INCORRECT_DATA("Передан некорректный запрос");
    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
