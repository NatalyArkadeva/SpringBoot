package task5.services;

import task5.person.Person;

import java.util.Optional;

public interface PersonService {
    Person getPersonByName(String name);

    Person getPersonById(int id);

    void deletePersonById(int id);

    Person save(Person person);
}
