package task5.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String series;
    private String number;
    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;
}
