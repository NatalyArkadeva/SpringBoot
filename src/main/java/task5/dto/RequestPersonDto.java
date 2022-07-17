package task5.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestPersonDto {
    private Integer id;
    private String name;
    private LocalDate birthday;
    private String surname;
    private String patronymic;
    private PassportDto passportDto;
}
