package task5.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task5.converter.PersonConverter;
import task5.entity.Department;
import task5.entity.Passport;
import task5.entity.Person;
import task5.repository.DepartmentRepository;
import task5.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;

@Component
public class UtilTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PersonConverter personConverter;

    public Person testPersonForPersonControllerTest() {
        Passport passport = new Passport("1234", "123456", LocalDate.of(2007, 4, 25));
        Department department = new Department("Department of Mysteries", 1);
        Person person = new Person("Henry", LocalDate.of(1987, 4, 25),
                "Ivanov", passport);
        person.addPersonToDepartment(department);
        departmentRepository.save(department);
        return personRepository.save(person);
    }

    public Person testPersonWithoutDepartmentTest() {
        Passport passport = new Passport("1234", "123456", LocalDate.of(2007, 4, 25));
        Person person = new Person("Henry", LocalDate.of(1987, 4, 25),
                "Ivanov", passport);
        return personRepository.save(person);
    }

    public List<Person> testListPersonForPersonControllerTest() {
        Passport passport1 = new Passport("1234", "123456", LocalDate.of(2007, 11, 12));
        Passport passport2 = new Passport("1234", "123457", LocalDate.of(2009, 7, 25));
        Passport passport3 = new Passport("1234", "123458", LocalDate.of(2009, 4, 25));

        Department department1 = new Department("Department of Mysteries", 1);
        Department department2 = new Department("Department of Magical Transportation", 2);

        Person person1 = new Person("Henry", LocalDate.of(1987, 6, 25),
                "Ivanov", passport1);
        person1.addPersonToDepartment(department1);
        Person person2 = new Person("Harry", LocalDate.of(1987, 7, 15),
                "Potter", passport2);
        person2.addPersonToDepartment(department2);
        Person person3 = new Person("Helen", LocalDate.of(1999, 4, 25),
                "Gold", passport3);
        person3.addPersonToDepartment(department2);
        departmentRepository.saveAll(List.of(department1, department2));
        return personRepository.saveAll(List.of(person1, person2, person3));
    }

    public List<Department> testListDepartmentsForDepartmentControllerTest() {
        Department department1 = new Department("Department of Mysteries", 1);
        Department department2 = new Department("Department of Magical Transportation", 2);
        Department department3 = new Department("Department of Magical Law Enforcement", 3);
        return departmentRepository.saveAll(List.of(department1, department2, department3));
    }

    public Department testDepartmentForDepartmentControllerTest() {
        Department department = new Department("Department of Mysteries", 1);
        return departmentRepository.save(department);
    }
}
