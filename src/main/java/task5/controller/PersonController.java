package task5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import task5.converter.PersonConverter;
import task5.dto.PersonDto;
import task5.person.Person;
import task5.services.PersonService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final PersonConverter personConverter;

    @GetMapping("/name/{name}")
    public Person getPerson(@PathVariable String name) {
        return personService.getPersonByName(name);
    }

    @PutMapping("/name/{id}")
    public Person replacePersonAge(@PathVariable Integer id, @RequestParam int age) {
        personService.getPersonById(id).setAge(age);
        return personService.save(personService.getPersonById(id));
    }

    @DeleteMapping("/name/{id}")
    public void deletePerson(@PathVariable int id) {
        personService.deletePersonById(id);
    }

    @GetMapping("/person/name/age/{name}/{age}")
    public Person getPersonByNameAge(@PathVariable String name, @PathVariable int age) {
        return personService.getPersonByNameAndAge(name, age);
    }

    @GetMapping("/person/name/{age}")
    public List<Person> getAllPerson(@PathVariable int age) {
        return personService.getAllPersonByAge(age);
    }

    @PostMapping("/person")
    public Person addNewPerson(@RequestBody Person person){
        return personService.save(person);
    }

    @GetMapping("/person/age/{age}")
    public List<PersonDto> getAllPersonWhereAgeOver(@PathVariable int age) {
        List<Person> personList = personService.getAllPersonWhereAgeOverSomeAge(age);
        return personConverter.entityToDto(personList);
    }
}
