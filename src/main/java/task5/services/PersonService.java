package task5.services;

import task5.entity.Person;

import java.time.LocalDate;
import java.util.List;

public interface PersonService {
    Person getPersonByName(String name);

    Person getPersonById(int id);

    void deletePersonById(int id);

    Person save(Person person);

    List<Person> saveAll(List<Person> personList);

    Person getPersonByNameAndBirthday(String name, LocalDate birthday);

    List<Person> getAllPersonByAge(int age);

    List<Person> getAllPersonOlderSomeAge(int age);

}
