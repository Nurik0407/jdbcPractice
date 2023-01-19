package peaksoft.servises;

import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {
    void createTable();

    void saveStudent(Student student);

    void findByID(Long idStudent);

    List<Student> findAll();

    void updateStudent(Long id,Student newStudent);

    void deletedStudent(Long id);

    List<Student> getAllStudentsSortByAge(String ascOrDesc);

    boolean checkByAge();

    void addColumnGender();

    Map<Gender, List<Student>> gruopByGender();

    void deleteAllStudents();
}
