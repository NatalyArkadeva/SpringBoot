package task5.converter;

import org.springframework.stereotype.Component;
import task5.dto.PassportDto;
import task5.person.Passport;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PassportConverter {
    public PassportDto entityToDto(Passport passport) {
        PassportDto passportDto = new PassportDto();
        passportDto.setSeries(passport.getSeries());
        passportDto.setNumber(passport.getNumber());
        passportDto.setDateOfIssue(passport.getDateOfIssue());
        return passportDto;
    }

    public List<PassportDto> entityToDto(List<Passport> passportList) {
        return passportList.stream()
                .map(passport -> entityToDto(passport))
                .collect(Collectors.toList());
    }
}
