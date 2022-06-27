package task5.services;

import task5.person.Person;

import java.util.List;

public interface PersonService {
    Person getPersonByName(String name);

    Person getPersonById(int id);

    void deletePersonById(int id);

    Person save(Person person);

    Person getPersonByNameAndAge(String name, int age);

    List<Person> getAllPersonByAge(int age);
}
