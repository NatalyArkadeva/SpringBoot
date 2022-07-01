package task5.person;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;
    private String address;
    private String mobile;

    public Person() {
    }

    public Person(String name, int age, String surname, String patronymic, LocalDate creationDate, String password, String address, String mobile) {
        this.name = name;
        this.age = age;
        this.surname = surname;
        this.patronymic = patronymic;
        this.creationDate = creationDate;
        this.password = password;
        this.address = address;
        this.mobile = mobile;
    }
}

