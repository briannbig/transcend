package brian.big.students.domain;

import brian.big.students.models.Student;
import brian.big.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;


@Service("admissionService")
public class AdmissionService {

    private static final int startAdmNo = 1000;

    @Autowired
    private StudentRepository repo;
    public Student admitStudent(Student student){
        Student lastStudent = repo.findTopByOrderByAdmissionNumberDesc();
        int lastAdmNo;
        if (lastStudent != null){
            lastAdmNo = lastStudent.getAdmissionNumber();
        }
        else lastAdmNo = startAdmNo;
        System.out.println("DEBUG:INSERT ------- last admission Number: "+ lastAdmNo);
        lastAdmNo++;
        System.out.println("DEBUG:INSERT ------- new admission Number: "+ lastAdmNo);
        student.setAdmissionNumber(lastAdmNo);

        Optional<Student> studentByAdmNo = repo.
                findByAdmissionNumber(student.getAdmissionNumber());
        if (studentByAdmNo.isPresent()){
            throw new IllegalStateException("admission number exists");
        }

        if (student.getDateOfBirth() == null){
            student.setDateOfBirth(LocalDate.of(2000, 1, 1));
        }
        if (student.getDateOfAdmission() == null){
            student.setDateOfAdmission(LocalDate.now());
        }

        repo.save(student);

        return student;
    }

    @Transactional
    public Student updateStudent(Student student){
        if (!student.getFirstName().isBlank() &&
                !student.getSurname().isBlank() &&
                student.getDateOfBirth() != null &&
                student.getDateOfAdmission() !=null
        ){
            Student studentById = repo.findById(student.getId())
                    .orElseThrow(() -> new IllegalStateException(""));

            if (!Objects.equals(studentById.getFirstName(), student.getFirstName())){
                studentById.setFirstName(student.getFirstName());
            }
            if (!Objects.equals(studentById.getSurname(), student.getSurname())){
                studentById.setSurname(student.getSurname());
            }
            if (!Objects.equals(studentById.getDateOfBirth(), student.getDateOfBirth())){
                studentById.setDateOfBirth(student.getDateOfBirth());
            }
            if (!Objects.equals(studentById.getDateOfAdmission(), student.getDateOfAdmission())){
                studentById.setDateOfAdmission(student.getDateOfAdmission());
            }

        }
        return student;
    }
}
