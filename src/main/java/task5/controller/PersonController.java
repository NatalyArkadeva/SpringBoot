package task5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import task5.person.Person;
import task5.person.SearchPerson;
import task5.services.PersonService;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class PersonController {
//    private List<Person> persons = new ArrayList<>();
//    {
//        persons.add(new Person("john", 20));
//        persons.add(new Person("joanna", 24));
//        persons.add(new Person("bred", 50));
//        persons.add(new Person("kate", 33));
//    }
//    @GetMapping("/person/name")
//    public Person getPerson(@RequestParam(value = "name") String name) {
//        return SearchPerson.findPerson(persons, name);
//    }
//    @GetMapping("/person/name/{name}")
//    public Person findPerson(@PathVariable String name) {
//        return SearchPerson.findPerson(persons, name);
//    }
//    @PostMapping("/person/name")
//    public String addNewPerson(@RequestBody Person person) {
//        persons.add(person);
//        return "Person with name " + person.getName() + " was add";
//    }
//    @PutMapping("/person/name")
//    public String updatePerson(@RequestBody Person person) {
//        if (persons.contains(SearchPerson.findPerson(persons, person.getName()))) {
//            for (Person p : persons) {
//                if (p.getName().equals(person.getName())) {
//                    p.setAge(person.getAge());
//                }
//            }
//            return "Person with name " + person.getName() + " was update";
//        } else {
//            persons.add(person);
//            return "Person with name " + person.getName() + " was add";
//            }
//    }
//    @DeleteMapping("/person/name/{name}")
//    public String deletePerson(@PathVariable String name) {
//        if (persons.contains(SearchPerson.findPerson(persons, name))) {
//            persons.remove(SearchPerson.findPerson(persons, name));
//            return "Person with name " + name + " was delete";
//        } else {
//            return "Person with name " + name + " not found";
//        }
//    }
    private final PersonService personService;

    @GetMapping("/name/{name}")
    public Person getPerson(@PathVariable String name){
        return personService.getPersonByName(name);
    }

    @PutMapping("/name/{id}")
    public Person replacePersonAge(@PathVariable Integer id, @RequestParam int age){
        personService.getPersonById(id).setAge(age);
        return  personService.save(personService.getPersonById(id));
    }

    @DeleteMapping("/name/{id}")
    public void deletePerson(@PathVariable int id){
        personService.deletePersonById(id);
    }
}
