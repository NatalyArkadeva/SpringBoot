package task5.person;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int age;
    private String surname;
    private String patronymic;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    private String password;
    private String passport;
    private String address;
    private String mobile;
}

