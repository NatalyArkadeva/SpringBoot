import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import task5.Application;
import task5.controller.PersonController;
import task5.converter.PersonConverter;
import task5.entity.Passport;
import task5.entity.Person;
import task5.exception.EntityException;
import task5.repository.DepartmentRepository;
import task5.repository.PersonRepository;
import task5.services.PersonServiceImp;
import task5.util.UtilTest;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonServiceImp personService;
    @Autowired
    private PersonController personController;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UtilTest util;
    @Autowired
    private PersonConverter personConverter;

    @BeforeEach
    public void deleteAll() {
        personRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @Test
    public void getPersonTest() throws Exception {
        final var person = util.testPersonForPersonControllerTest();
        this.mockMvc.perform(
                        get("/name/Henry").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(person)))
                .andExpect(status().isOk());

        assertEquals(personController.getPerson("Henry"), person);
        assertThrows(EntityException.class, () -> {
            personController.getPerson("Harry");
        }, "Пользователь не найден");
        assertThrows(EntityException.class, () -> {
            personController.getPerson("1234");
        }, "Передан некорректный запрос");
    }

    @Test
    public void getPersonDtoTest() throws Exception {
        final var personDto = personConverter.entityToDto(util.testPersonForPersonControllerTest());
        this.mockMvc.perform(
                        get("/name_dto/Henry").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(personDto)))
                .andExpect(status().isOk());

        assertEquals(personController.getPersonDto("Henry"), personDto);
        assertThrows(EntityException.class, () -> {
            personController.getPersonDto("Harry");
        }, "Пользователь не найден");
        assertThrows(EntityException.class, () -> {
            personController.getPersonDto("1234");
        }, "Передан некорректный запрос");
    }

    @Test
    public void changePersonBirthdayTest() throws Exception {
        final var person = util.testPersonForPersonControllerTest();
        this.mockMvc.perform(
                        put("/name_id/{id}", person.getId())
                                .param("birthday", "1959-12-25")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(person)))
                .andExpect(status().isOk());

        assertEquals(personController.changePersonBirthday(person.getId(), LocalDate.of(1959, 12, 25)),
                ResponseEntity.status(HttpStatus.OK).body("Birthday was successfully changed"));
        assertThrows(EntityException.class, () -> {
            personService.getPersonById(1255);
        }, "Пользователь не найден");
    }

    @Test
    public void deletePersonTest() throws Exception {
        final var person = util.testPersonForPersonControllerTest();
        int personId = person.getId();
        int depId = person.getDepartment().getId();
        this.mockMvc.perform(
                        delete("/name/{id}", personId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(person)))
                .andExpect(status().isOk());

        assertSame(0, departmentRepository.findById(depId).get().getCountPerson());
        assertThrows(EntityException.class, () -> {
            personService.getPersonById(personId);
        }, "Пользователь не найден");
    }

    @Test
    public void getPersonByNameAgeTest() throws Exception {
        final var personDto = personConverter.responsePersonToDto(util.testPersonForPersonControllerTest());
        String personName = personDto.getName();
        LocalDate personBirthday = personDto.getBirthday();
        this.mockMvc.perform(
                        get("/person/name/age/{name}/{birthday}", personName, personBirthday)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(personDto)))
                .andExpect(status().isOk());

        assertEquals(personController.getPersonByNameAge(personName, personBirthday), personDto);
    }

    @Test
    public void getAllPersonByAgeTest() throws Exception {
        final var listPersonDto = util.testListPersonForPersonControllerTest();
        this.mockMvc.perform(
                        get("/person/name/{age}", 35)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(listPersonDto)))
                .andExpect(status().isOk());

        assertEquals(2, personController.getAllPersonByAge(35).size());
    }

    @Test
    public void getAllPersonOlderSomeAgeTest() throws Exception {
        final var listPersonDto = personConverter.responsePersonToDto(util.testListPersonForPersonControllerTest());
        this.mockMvc.perform(
                        get("/person/name/{age}", 30)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(listPersonDto)))
                .andExpect(status().isOk());

        assertEquals(2, personController.getAllPersonOlderSomeAge(30).size());
    }

    @Test
    public void addNewPersonTest() throws Exception {
        final var person = util.testPersonForPersonControllerTest();
        this.mockMvc.perform(
                        post("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(result -> assertEquals("Henry", personRepository.findByName(person.getName()).get().getName()));

//        assertEquals("Henry", personRepository.findByName(person.getName()).get().getName());
    }

    @Test
    public void addNewInvalidPersonTest() throws Exception {
        Person person = new Person(" ", "123", "456", LocalDate.of(2009, 12, 25), "qwerty",
                new Passport("123", "12W456", LocalDate.of(2007, 4, 25)));
        this.mockMvc.perform(
                        post("/person")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(person)))
                .andExpect(status().is4xxClientError());

        assertThrows(ConstraintViolationException.class, () -> {
            personController.addNewPerson(person);
        });
    }

}
