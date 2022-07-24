package task5.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task5.dto.DepartmentDto;
import task5.entity.Department;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentConverter {
    @Autowired
    private PersonConverter personConverter;

    public DepartmentDto departmentToDto(Department department) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(department.getDepartmentName());
        departmentDto.setCountPerson(department.getCountPerson());
        return departmentDto;
    }

    public List<DepartmentDto> departmentToDto(List<Department> departments) {
        return departments.stream()
                .map(department -> departmentToDto(department))
                .collect(Collectors.toList());
    }
}
