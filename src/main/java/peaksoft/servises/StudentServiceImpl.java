package peaksoft.servises;

import peaksoft.dao.StudentDao;
import peaksoft.dao.StudentDaoImpl;
import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = new StudentDaoImpl();

    public StudentServiceImpl() throws SQLException {
    }

    @Override
    public void createTable() {
        studentDao.createTable();
    }

    @Override
    public void saveStudent(Student student) {
        studentDao.saveStudent(student);
    }

    @Override
    public void findByID(Long idStudent) {
        studentDao.findByID(idStudent);
    }

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public void updateStudent(Long id, Student newStudent) {
        studentDao.updateStudent(id, newStudent);
    }

    @Override
    public void deletedStudent(Long id) {
        studentDao.deletedStudent(id);
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
        return studentDao.getAllStudentsSortByAge(ascOrDesc);
    }

    @Override
    public boolean checkByAge() {
        return studentDao.checkByAge();
    }

    @Override
    public void addColumnGender() {
        studentDao.addColumnGender();
    }

    @Override
    public Map<Gender, List<Student>> gruopByGender() {
      return   studentDao.gruopByGender();
    }

    @Override
    public void deleteAllStudents() {
        studentDao.deleteAllStudents();
    }
}
