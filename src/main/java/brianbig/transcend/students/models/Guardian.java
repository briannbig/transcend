package brianbig.transcend.students.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="parent")
public class Guardian {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "surname")
    private String surname;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "national_id")
    private int nationalId;
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "telephone")
    private String telephone;

}