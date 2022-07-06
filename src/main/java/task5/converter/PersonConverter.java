package task5.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task5.dto.RequestPersonDto;
import task5.dto.ResponsePersonDto;
import task5.entity.Person;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    @Autowired
    private PassportConverter passportConverter;

    public RequestPersonDto entityToDto(Person person) {
        RequestPersonDto dto = new RequestPersonDto();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setAge(person.getAge());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        dto.setPassportDto(passportConverter.entityToDto(person.getPassport()));
        return dto;
    }

    public List<RequestPersonDto> entityToDto(List<Person> person) {
        return person.stream()
                .map(p -> entityToDto(p))
                .collect(Collectors.toList());
    }

    public ResponsePersonDto responsePersonToDto(Person person) {
        ResponsePersonDto personDto = new ResponsePersonDto();
        personDto.setName(person.getName());
        personDto.setSurname(person.getSurname());
        personDto.setAge(person.getAge());
        return personDto;
    }

    public List<ResponsePersonDto> responsePersonToDto(List<Person> person) {
        return person.stream()
                .map(p -> responsePersonToDto(p))
                .collect(Collectors.toList());
    }
}
