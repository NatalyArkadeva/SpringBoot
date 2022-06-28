package task5.converter;

import org.springframework.stereotype.Component;
import task5.dto.PersonDto;
import task5.person.Person;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {
    public PersonDto entityToDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setAge(person.getAge());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        return dto;
    }

    public List<PersonDto> entityToDto(List<Person> person) {
        return person.stream()
                .map(p -> entityToDto(p))
                .collect(Collectors.toList());
    }

    public Person dtoToEntity(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setName(personDto.getName());
        person.setAge(personDto.getAge());
        person.setSurname(personDto.getSurname());
        person.setPatronymic(personDto.getPatronymic());
        return person;
    }

    public List<Person> dtoToEntity(List<PersonDto> personDto) {
        return personDto.stream()
                .map(pDto -> dtoToEntity(pDto))
                .collect(Collectors.toList());
    }
}
