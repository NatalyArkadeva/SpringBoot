package task5.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import task5.repository.PersonRepository;
import task5.util.UtilTest;

@Configuration
@EnableScheduling
public class PersonScheduler {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UtilTest test;

    @Scheduled(fixedDelay = 10000)
    public void schedulePersonWithPatronymicIsNull() {
        personRepository.deleteAll();
        test.testListPersonForPersonControllerTest();
        System.out.println(personRepository.findAllPerson());
        personRepository.deleteAll();
    }
}
