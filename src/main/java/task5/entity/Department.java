package task5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "department_name")
    private String departmentName;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
    private List<Person> persons;

    public void addPersonToDepartment(Person person){
        if(persons==null){
            persons = new ArrayList<>();
        }
        persons.add(person);
        person.setDepartment(this);
    }
}
