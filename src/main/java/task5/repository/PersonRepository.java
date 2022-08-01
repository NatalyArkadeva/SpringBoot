package task5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import task5.entity.Person;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByName(String name);

    Optional<Person> findByNameAndBirthday(String name, LocalDate birthday);

    Optional<Person> findByNameAndSurname(String name, String surname);

    @Query(value = " select p from Person p where p.patronymic is null")
    List<Person> findAllPersonWithoutPatronymic();
}
