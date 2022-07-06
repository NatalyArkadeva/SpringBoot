package task5.dto;

import lombok.Data;

@Data
public class RequestPersonDto {
    private Integer id;
    private String name;
    private int age;
    private String surname;
    private String patronymic;
    private PassportDto passportDto;
}
