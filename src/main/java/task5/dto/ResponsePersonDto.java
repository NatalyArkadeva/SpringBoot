package task5.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponsePersonDto {
    private String name;
    private String surname;
    private LocalDate birthday;
}
