package task5.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "passport")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp = "[\\d]{4}", message = "Поле 'серия' содержит недопустимые символы")
    private String series;
    @Pattern(regexp = "[\\d]{6}", message = "Поле 'номер' содержит недопустимые символы")
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
