package task5.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task5.converter.DepartmentConverter;
import task5.dto.DepartmentDto;
import task5.entity.Department;
import task5.services.DepartmentService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final DepartmentConverter departmentConverter;

    @GetMapping("/department")
    public List<DepartmentDto> getAllDepartment(){
        log.debug("Количество записей " + departmentService.getAllDepartment().size());
        log.info("Количество записей " + departmentService.getAllDepartment().size());
        return departmentConverter.departmentToDto(departmentService.getAllDepartment());
    }

    @PostMapping("/department")
    public ResponseEntity addNewDepartment(@RequestBody Department department){
        departmentService.addNewDepartment(department);
        return ResponseEntity.status(HttpStatus.OK).body("Department " + department.getDepartmentName() + " was successfully added");
    }

    @PutMapping("/department/person/{departmentName}/{personName}/{personSurname}")
    public ResponseEntity addPersonToDepartment(@PathVariable String departmentName, @PathVariable String personName, @PathVariable String personSurname){
        departmentService.addPersonToDepartment(departmentName,personName,personSurname);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Person " + personName + " " + personSurname + " was successfully added to department " + departmentName);
    }

    @DeleteMapping("/department/{departmentName}/{personName}/{personSurname}")
    public ResponseEntity deletePersonFromDepartment(@PathVariable String departmentName, @PathVariable String personName, @PathVariable String personSurname){
        departmentService.deletePersonFromDepartment(departmentName, personName, personSurname);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Person " + personName + " " + personSurname + " was successfully deleted from department " + departmentName);
    }

    @DeleteMapping("/department/{departmentName}")
    public ResponseEntity deleteDepartment(@PathVariable String departmentName){
        departmentService.deleteDepartment(departmentName);
        return ResponseEntity.status(HttpStatus.OK).body("Department " + departmentName + " was successfully deleted");
    }
}
