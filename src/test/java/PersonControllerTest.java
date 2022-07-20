import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.TransitionFrom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import task5.Application;
import task5.controller.PersonController;
import task5.entity.Department;
import task5.entity.Passport;
import task5.entity.Person;
import task5.exception.EntityException;
import task5.repository.DepartmentRepository;
import task5.repository.PersonRepository;
import task5.services.PersonServiceImp;

import javax.transaction.TransactionScoped;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.Assert.*;

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

    @BeforeEach
    public void deleteAll(){
        personRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @Test
    public void getPersonTest() throws Exception {
        Passport passport = new Passport("1234", "123456", LocalDate.of(1997, 4, 25));
        Department department = new Department("IT", new ArrayList<>());
        departmentRepository.save(department);
        Person person = new Person("Henry", LocalDate.of(1987, 4, 25),
                "Ivanov", passport, department);
        personRepository.save(person);
        this.mockMvc.perform(
                    get("/name/Henry").contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                            .content(this.mapper.writeValueAsString(person)))
                    .andExpect(status().isOk());

        assertEquals(personController.getPerson("Henry").getPassport(), person.getPassport());
        assertThrows(EntityException.class, () -> {personController.getPerson("Harry");}, "Пользователь не найден");
        assertThrows(EntityException.class, () -> {personController.getPerson("1234");}, "Передан некорректный запрос");
    }

    @Test
    public void replacePersonAgeTest() throws Exception {
        Passport passport = new Passport("1234", "123456", LocalDate.of(1997, 4, 25));
        Department department = new Department("IT", new ArrayList<>());
        departmentRepository.save(department);
        Person person = new Person("Henry", LocalDate.of(1987, 4, 25),
                "Ivanov", passport, department);
        personRepository.save(person);
        this.mockMvc.perform(
                        put("/name_id/{id}",person.getId())
                                .param("birthday", "1959-12-25")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(person)))
                .andExpect(status().isOk());

        assertEquals(personController.replacePersonAge(person.getId(),LocalDate.of(1959, 12, 25)).getBirthday(),
                LocalDate.of(1959, 12, 25));
        assertThrows(EntityException.class, () -> {personService.getPersonById(1255);}, "Пользователь не найден");
    }

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
//        when(personService.getPersonByName("Henry")).thenReturn(person);
    }

}
