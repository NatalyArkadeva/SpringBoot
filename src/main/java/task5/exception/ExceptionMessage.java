package task5.exception;

public enum ExceptionMessage {
    PERSON_NOT_FOUND("Пользователь не найден"),
    INCORRECT_DATA("Передан некорректный запрос"),
    DEPARTMENT_NOT_FOUND("Отдел не найден"),
    PERSON_NOT_FOUND_IN_DEPARTMENT("Пользователь не найден в отделе"),
    INCORRECT_MOBILE("Номер телефона не соответствует допустимому формату"),
    INCORRECT_PASSWORD("Пароль не соответствует допустимому формату"),
    INCORRECT_BIRTHDAY("Представителям поколения Z регистрация недоступна");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
