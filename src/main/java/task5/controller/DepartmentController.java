package task5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task5.converter.DepartmentConverter;
import task5.dto.DepartmentDto;
import task5.entity.Department;
import task5.services.DepartmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentConverter departmentConverter;

    @GetMapping("/department")
    public List<DepartmentDto> getAllDepartment(){
        return departmentConverter.departmentToDto(departmentService.getAllDepartment());
    }

    @PostMapping("/department")
    public ResponseEntity addNewDepartment(@RequestBody Department department){
        departmentService.addNewDepartment(department);
        return ResponseEntity.status(HttpStatus.OK).body("Department " + department.getDepartmentName() + " was successfully added");
    }

    @PutMapping("/department/person/{department}/{personName}/{personSurname}")
    public ResponseEntity addPersonToDepartment(@PathVariable String department, @PathVariable String personName, @PathVariable String personSurname){
        Department dep = departmentService.addPersonToDepartment(department,personName,personSurname);
        departmentService.save(dep);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Person " + personName + " " + personSurname + " was successfully added to department " + department);
    }

    @PutMapping("/department/{department}/{personName}/{personSurname}")
    public ResponseEntity deletePersonFromDepartment(@PathVariable String department, @PathVariable String personName, @PathVariable String personSurname){
        Department dep = departmentService.deletePersonFromDepartment(department, personName, personSurname);
        departmentService.save(dep);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Person " + personName + " " + personSurname + " was successfully deleted from department " + department);
    }

    @DeleteMapping("/department/{name}")
    public ResponseEntity deleteDepartment(@PathVariable String name){
        departmentService.deleteDepartment(name);
        return ResponseEntity.status(HttpStatus.OK).body("Department " + name + " was successfully deleted");
    }
}
