package task5.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentDto {
    private String name;
    private List<ResponsePersonDto> persons;
}
