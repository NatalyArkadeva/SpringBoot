package task5.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task5.dto.PersonDto;
import task5.person.Person;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    @Autowired
    private PassportConverter passportConverter;

    public PersonDto entityToDto(Person person) {
        PersonDto dto = new PersonDto();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setAge(person.getAge());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        dto.setPassportDto(passportConverter.entityToDto(person.getPassport()));
        return dto;
    }

    public List<PersonDto> entityToDto(List<Person> person) {
        return person.stream()
                .map(p -> entityToDto(p))
                .collect(Collectors.toList());
    }
}
