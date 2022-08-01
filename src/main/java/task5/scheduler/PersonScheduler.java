package task5.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import task5.repository.PersonRepository;

@Configuration
@EnableScheduling
public class PersonScheduler {

    @Autowired
    private PersonRepository personRepository;

    @Scheduled(fixedDelay = 10000)
    public void schedulePersonWithPatronymicIsNull() {
        System.out.println(personRepository.findAllPersonWithoutPatronymic());
    }
}
