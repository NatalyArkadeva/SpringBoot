package task5.entity;

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
    //    @Pattern(regexp = "19[\\d]{2}-(0[1-9])|(1[0-2])-(01)|([1-2][0-9])|(3[0-1])")
    private LocalDate birthday;
    private String surname;
    private String patronymic;
    @Column(name = "creation_date")
    private LocalDate creationDate;
    //    @Pattern(regexp = "[A-Z0-9]{8,10}", message = "Пароль не соответствует допустимому формату")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;
    private String address;
    //    @Pattern(regexp = "^(\\+7|8)[\\d]{10}")
    private String mobile;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public Person() {
    }

    public Person(String name, LocalDate birthday, String surname, Passport passport) {
        this.name = name;
        this.birthday = birthday;
        this.surname = surname;
        this.passport = passport;
    }

    public void addPersonToDepartment(Department dep) {
        if (this.getDepartment() != null) {
            deletePersonFromDepartment(this.getDepartment());
        }
        this.setDepartment(dep);
        dep.setCountPerson(dep.getCountPerson() + 1);
    }

    public void deletePersonFromDepartment(Department dep) {
        this.setDepartment(null);
        dep.setCountPerson(dep.getCountPerson() - 1);
    }
}

