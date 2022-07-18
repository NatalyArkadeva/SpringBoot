import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import task5.Application;
import task5.entity.Department;
import task5.entity.Passport;
import task5.entity.Person;
import task5.repository.DepartmentRepository;
import task5.repository.PersonRepository;
import task5.services.PersonServiceImp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonServiceImp personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

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
        when(personService.getPersonByName("Henry")).thenReturn(person);
        this.mockMvc.perform(
                    get("/person/Henry"))
                    .andExpect(status().isOk());
    }

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));

    }

}
