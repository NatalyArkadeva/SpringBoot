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

    public Department getDepartment(int id) {
        return departmentRepository.findById(id).orElseThrow(() -> new EntityException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
    }

    public void addNewDepartment(Department department) {
        util.throwExceptionIfNameIncorrect(department.getDepartmentName());
        util.throwExceptionIfDepartmentAlreadyExists(department.getDepartmentName());
        departmentRepository.save(department);
    }

    public void addPersonToDepartment(String departmentName, String personName, String personSurname) {
        util.throwExceptionIfNameIncorrect(departmentName);
        Person person = personRepository.findByNameAndSurname(personName, personSurname)
                .orElseThrow(() -> new EntityException(ExceptionMessage.PERSON_NOT_FOUND));
        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new EntityException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        person.addPersonToDepartment(department);
        personRepository.save(person);
        departmentRepository.save(department);
    }

    public void deletePersonFromDepartment(String departmentName, String personName, String personSurname) {
        util.throwExceptionIfNameIncorrect(departmentName);
        Person person = personRepository.findByNameAndSurname(personName, personSurname)
                .orElseThrow(() -> new EntityException(ExceptionMessage.PERSON_NOT_FOUND));
        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new EntityException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        if (person.getDepartment().getId() == department.getId()) {
            person.deletePersonFromDepartment(department);
        } else {
            throw new EntityException(ExceptionMessage.PERSON_NOT_FOUND_IN_DEPARTMENT);
        }
        personRepository.save(person);
        departmentRepository.save(department);
    }

    public void deleteDepartment(String departmentName) {
        util.throwExceptionIfNameIncorrect(departmentName);
        Department department = departmentRepository.findByDepartmentName(departmentName)
                .orElseThrow(() -> new EntityException(ExceptionMessage.DEPARTMENT_NOT_FOUND));
        List<Person> persons = personRepository.findAll();
        persons.stream()
                .filter(person -> person.getDepartment().getId() == department.getId())
                .forEach(person -> person.setDepartment(null));
        personRepository.saveAll(persons);
        departmentRepository.delete(department);
    }
}
