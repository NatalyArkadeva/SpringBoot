package task5.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "department_name")
    private String departmentName;
    private int build;
    @Column(name = "count_person")
    private int countPerson;

    public Department() {
    }

    public Department(String departmentName, int build) {
        this.departmentName = departmentName;
        this.build = build;
        this.countPerson = 0;
    }
}
