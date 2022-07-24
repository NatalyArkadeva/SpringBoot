package task5.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import task5.entity.Person;
import task5.exception.EntityException;
import task5.exception.ExceptionMessage;
import task5.repository.DepartmentRepository;
import task5.repository.PersonRepository;
import task5.util.Util;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;
    private final DepartmentRepository departmentRepository;
    private final Util util;
    private LocalDate now;

    @Transactional
    @Override
    public Person getPersonByName(String name) {
        util.throwExceptionIfNameIncorrect(name);
        return personRepository.findByName(name).orElseThrow(() -> new EntityException(ExceptionMessage.PERSON_NOT_FOUND));
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityException(ExceptionMessage.PERSON_NOT_FOUND));
    }

    @Override
    public void deletePersonById(int id) {
        var person = personRepository.findById(id).orElseThrow(() -> new EntityException(ExceptionMessage.PERSON_NOT_FOUND));
        var department = person.getDepartment();
        person.deletePersonFromDepartment(department);
        personRepository.deleteById(id);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> saveAll(List<Person> personList) {
        return personRepository.saveAll(personList);
    }

    @Override
    public Person getPersonByNameAndBirthday(String name, LocalDate birthday) {
        util.throwExceptionIfNameIncorrect(name);
        return personRepository.findByNameAndBirthday(name, birthday).orElseThrow(() -> new EntityException(ExceptionMessage.PERSON_NOT_FOUND));
    }

    @Override
    public List<Person> getAllPersonByAge(int age) {
        now = LocalDate.now();
        return personRepository.findAll().stream()
                .filter(person -> person.getBirthday().plusYears(age).isBefore(now) &&
                        person.getBirthday().plusYears(age).isAfter(now.minusYears(1)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getAllPersonOlderSomeAge(int age) {
        now = LocalDate.now();
        return personRepository.findAll().stream()
                .filter(person -> person.getBirthday().plusYears(age).isBefore(now))
                .collect(Collectors.toList());
    }
}
