package task5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task5.person.Person;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Person findByName(String name);

    Person findById(int id);

    Person save(Person person);

    List<Person> findByAge(int age);

    @Query(value = " select p from Person p where p.name like :name and p.age = :age")
    Person findByNameAndAge(String name, int age);

    @Query(value = " select p from Person p where p.age > :age")
    List<Person> findAllByAge(int age);

}
