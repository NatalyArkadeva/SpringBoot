package task5.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PassportDto {
    private String series;
    private String number;
    private LocalDate dateOfIssue;
}
