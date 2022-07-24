import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import task5.Application;
import task5.controller.DepartmentController;
import task5.converter.DepartmentConverter;
import task5.entity.Department;
import task5.exception.EntityException;
import task5.repository.DepartmentRepository;
import task5.repository.PersonRepository;
import task5.services.DepartmentServiceImp;
import task5.util.UtilTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DepartmentController departmentController;
    @Autowired
    private DepartmentServiceImp departmentService;
    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private UtilTest util;

    @BeforeEach
    public void deleteAll() {
        personRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @Test
    public void getAllDepartmentTest() throws Exception {
        final var listDepartmentDto = departmentConverter.departmentToDto(
                util.testListDepartmentsForDepartmentControllerTest());
        this.mockMvc.perform(
                        get("/department").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(listDepartmentDto)))
                .andExpect(status().isOk());

        assertEquals(departmentController.getAllDepartment(), listDepartmentDto);
    }

    @Test
    public void addNewDepartmentTest() throws Exception {
        Department department = new Department("Department of Mysteries", 1);
        this.mockMvc.perform(
                        post("/department")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(department)))
                .andExpect(status().isOk());

        assertEquals(departmentRepository.findByDepartmentName(department.getDepartmentName()).get().getDepartmentName(),
                department.getDepartmentName());
        assertThrows(EntityException.class, () -> {
                    departmentController.addNewDepartment(department);
                },
                "Отдел уже существует");
    }

    @Test
    public void addPersonToDepartmentTest() throws Exception {
        final var department = util.testDepartmentForDepartmentControllerTest();
        final var person = util.testPersonWithoutDepartmentTest();
        this.mockMvc.perform(
                        put("/department/person/{departmentName}/{personName}/{personSurname}", department.getDepartmentName(),
                                person.getName(), person.getSurname())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(department)))
                .andExpect(status().isOk());

        assertEquals(departmentRepository.findByDepartmentName(department.getDepartmentName()).get().getCountPerson(),
                department.getCountPerson() + 1);
    }

    @Test
    public void deletePersonFromDepartmentTest() throws Exception {
        final var person = util.testPersonForPersonControllerTest();
        final var department = person.getDepartment();
        int personId = person.getId();
        this.mockMvc.perform(
                        delete("/department/{departmentName}/{personName}/{personSurname}", department.getDepartmentName(),
                                person.getName(), person.getSurname())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(department)))
                .andExpect(status().isOk());

        assertEquals(departmentRepository.findByDepartmentName(department.getDepartmentName()).get().getCountPerson(),
                department.getCountPerson() - 1);
        assertEquals(null, personRepository.findById(personId).get().getDepartment());
    }

    @Test
    public void deleteDepartmentTest() throws Exception {
        final var person = util.testPersonForPersonControllerTest();
        final var department = person.getDepartment();
        int personId = person.getId();
        int departmentId = department.getId();
        this.mockMvc.perform(
                        delete("/department/{departmentName}", department.getDepartmentName())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(department)))
                .andExpect(status().isOk());

        assertEquals(null, personRepository.findById(personId).get().getDepartment());
        assertThrows(EntityException.class, () -> {
                    departmentService.getDepartment(departmentId);
                },
                "Отдел не найден");
    }
}
