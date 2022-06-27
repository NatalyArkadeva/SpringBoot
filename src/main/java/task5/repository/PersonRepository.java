package task5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task5.person.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByName(String str);

    Person findById(int id);

    Person save(Person person);

    List<Person> findByAge(int age);

}
