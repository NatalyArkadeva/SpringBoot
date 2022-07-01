package task5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task5.person.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByName(String name);

    List<Person> findByAge(int age);

    Optional<Person> findByNameAndAge(String name, int age);

    @Query(value = " select p from Person p where p.age > :age")
    List<Person> findAllByAge(int age);
}
