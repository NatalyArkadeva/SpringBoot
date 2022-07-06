package task5.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task5.entity.Department;
import task5.entity.Person;
import task5.exception.EntityException;
import task5.exception.ExceptionMessage;
import task5.repository.DepartmentRepository;
import task5.repository.PersonRepository;
import task5.util.Util;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImp implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final Util util;
    private final PersonRepository personRepository;

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public void addNewDepartment(Department department) {
        util.throwExceptionIfNameIncorrect(department.getDepartmentName());
        departmentRepository.save(department);
    }

    public Department addPersonToDepartment(String departmentName, String personName, String personSurname) {
        util.throwExceptionIfNameIncorrect(departmentName);
        Person person = personRepository.findByNameAndSurname(personName, personSurname)
                .orElseThrow(() -> new EntityException(ExceptionMessage.PERSON_NOT_FOUND));
        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new EntityException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        department.addPersonToDepartment(person);
        return department;
    }

    public Department deletePersonFromDepartment(String departmentName, String personName, String personSurname) {
        util.throwExceptionIfNameIncorrect(departmentName);
        Person person = personRepository.findByNameAndSurname(personName, personSurname)
                .orElseThrow(() -> new EntityException(ExceptionMessage.PERSON_NOT_FOUND));
        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new EntityException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        if (department.getPersons().contains(person)) {
            department.getPersons().remove(person);
            person.setDepartment(null);
        } else {
            throw new EntityException(ExceptionMessage.PERSON_NOT_FOUND_IN_DEPARTMENT);
        }
        return department;
    }

    public void deleteDepartment(String departmentName) {
        util.throwExceptionIfNameIncorrect(departmentName);
        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new EntityException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        department.getPersons().stream()
                .forEach(person -> person.setDepartment(null));
        departmentRepository.delete(department);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }
}
