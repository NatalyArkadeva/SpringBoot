package task5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import task5.converter.PersonConverter;
import task5.dto.RequestPersonDto;
import task5.entity.Person;
import task5.services.PersonServiceImp;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonServiceImp personService;
    private final PersonConverter personConverter;

    @GetMapping("/")
    public String greeting() {
        return "Hello, World";
    }

    @GetMapping("/name/{name}")
    public Person getPerson(@PathVariable String name) {
        return personService.getPersonByName(name);
    }

    @GetMapping("/name_dto/{name}")
    public RequestPersonDto getPersonDto(@PathVariable String name) {
        return personConverter.entityToDto(personService.getPersonByName(name));
    }

    @PutMapping("/name_id/{id}")
    public Person replacePersonAge(@PathVariable Integer id, @Valid @RequestParam LocalDate birthday) {
        personService.getPersonById(id).setBirthday(birthday);
        return personService.save(personService.getPersonById(id));
    }

    @DeleteMapping("/name/{id}")
    public void deletePerson(@PathVariable int id) {
        personService.deletePersonById(id);
    }

    @GetMapping("/person/name/age/{name}/{birthday}")
    public Person getPersonByNameAge(@PathVariable String name, @PathVariable LocalDate birthday) {
        return personService.getPersonByNameAndBirthday(name, birthday);
    }

    @GetMapping("/person/name/{age}")
    public List<Person> getAllPersonByAge(@PathVariable int age) {
        return personService.getAllPersonByAge(age);
    }

    @PostMapping("/person")
    public Person addNewPerson(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/person/age/{age}")
    public List<RequestPersonDto> getAllPersonOlderSomeAge(@PathVariable int age) {
        List<Person> personList = personService.getAllPersonOlderSomeAge(age);
        return personConverter.entityToDto(personList);
    }

    @PostMapping("/persons")
    public List<Person> addAllNewPerson(@Validated @RequestBody List<Person> person) {
        return personService.saveAll(person);
    }
}
