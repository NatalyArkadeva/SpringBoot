package task5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task5.converter.PersonConverter;
import task5.dto.RequestPersonDto;
import task5.dto.ResponsePersonDto;
import task5.entity.Person;
import task5.services.PersonServiceImp;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

//@Validated
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonServiceImp personService;
    private final PersonConverter personConverter;

    @GetMapping("/name/{name}")
    public Person getPerson(@PathVariable String name) {
        return personService.getPersonByName(name);
    }

    @GetMapping("/name_dto/{name}")
    public RequestPersonDto getPersonDto(@PathVariable String name) {
        return personConverter.entityToDto(personService.getPersonByName(name));
    }

    @PutMapping("/name_id/{id}")
    public ResponseEntity changePersonBirthday(@PathVariable Integer id, @RequestParam LocalDate birthday) {
        personService.getPersonById(id).setBirthday(birthday);
        personService.save(personService.getPersonById(id));
        return ResponseEntity.status(HttpStatus.OK).body("Birthday was successfully changed");
    }

    @DeleteMapping("/name/{id}")
    public ResponseEntity deletePerson(@PathVariable int id) {
        personService.deletePersonById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Person with id " + id + " was successfully deleted");
    }

    @GetMapping("/person/name/age/{name}/{birthday}")
    public ResponsePersonDto getPersonByNameAge(@PathVariable String name, @PathVariable LocalDate birthday) {
        return personConverter.responsePersonToDto(personService.getPersonByNameAndBirthday(name, birthday));
    }

    @GetMapping("/person/name/{age}")
    public List<Person> getAllPersonByAge(@PathVariable int age) {
        return personService.getAllPersonByAge(age);
    }

    @PostMapping("/person")
    public ResponseEntity addNewPerson(@RequestBody @Valid Person person) {
        personService.save(person);
        return ResponseEntity.status(HttpStatus.OK).body("Person was successfully saved");
    }

    @GetMapping("/person/age/{age}")
    public List<RequestPersonDto> getAllPersonOlderSomeAge(@PathVariable int age) {
        List<Person> personList = personService.getAllPersonOlderSomeAge(age);
        return personConverter.entityToDto(personList);
    }

    @PostMapping("/persons")
    public ResponseEntity addAllNewPerson(@RequestBody @Valid List<Person> person) {
        personService.saveAll(person);
        return ResponseEntity.status(HttpStatus.OK).body("All persons was successfully saved");
    }
}
