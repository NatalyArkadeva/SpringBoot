package task5.person;

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

    public Passport() {
    }

    public Passport(String series, String number, LocalDate dateOfIssue) {
        this.series = series;
        this.number = number;
        this.dateOfIssue = dateOfIssue;
    }
}
