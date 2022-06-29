package task5.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task5.exception.ExceptionMessage;
import task5.exception.PersonException;
import task5.person.Person;
import task5.repository.PersonRepository;
import task5.util.Util;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService {

    private final PersonRepository personRepository;
    private final Util util;

    @Override
    public Person getPersonByName(String name) {
        util.throwExceptionIfNameIncorrect(name);
        return personRepository.findByName(name).orElseThrow(() -> new PersonException(ExceptionMessage.NOT_FOUND));
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.findById(id).orElseThrow(() -> new PersonException(ExceptionMessage.NOT_FOUND));
    }

    @Override
    public void deletePersonById(int id) {
        personRepository.findById(id).orElseThrow(() -> new PersonException(ExceptionMessage.NOT_FOUND));
        personRepository.deleteById(id);
    }

    @Override
    public Person save(Person person) {

        return personRepository.save(person);
    }

    @Override
    public Person getPersonByNameAndAge(String name, int age) {
        util.throwExceptionIfNameIncorrect(name);
        return personRepository.findByNameAndAge(name, age).orElseThrow(() -> new PersonException(ExceptionMessage.NOT_FOUND));
    }

    @Override
    public List<Person> getAllPersonByAge(int age) {
        return personRepository.findByAge(age);
    }

    @Override
    public List<Person> getAllPersonWhereAgeOverSomeAge(int age) {
        return personRepository.findAllByAge(age);
    }
}
