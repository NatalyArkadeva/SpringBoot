package task5.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import task5.person.Person;
import task5.repository.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImp implements PersonService{

    private final PersonRepository personRepository;

    @Override
    public Person getPersonByName(String name) {
        return personRepository.findByName(name);
    }

    @Override
    public Person getPersonById(int id) {
        return personRepository.findById(id);
    }

    @Override
    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public Person getPersonByNameAndAge(String name, int age) {
        return personRepository.findByAge(age)
                .stream()
                .filter(person -> person.getName().equals(name))
                .findAny().get();
    }

    @Override
    public List<Person> getAllPersonByAge(int age) {
        return personRepository.findByAge(age);
    }
}
