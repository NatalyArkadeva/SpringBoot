import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import task5.Application;
import task5.repository.DepartmentRepository;
import task5.repository.PersonRepository;
import task5.util.UtilTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class SchedulerTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UtilTest util;

    @BeforeEach
    public void deleteAll() {
        personRepository.deleteAll();
        departmentRepository.deleteAll();
    }

    @Test
    public void personSchedulerTest() throws Exception {
        final var person = util.testListPersonForPersonControllerTest();

        assertEquals(1, personRepository.findAllPersonWithoutPatronymic().size());
    }
}
