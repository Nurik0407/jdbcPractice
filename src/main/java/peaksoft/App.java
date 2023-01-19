package peaksoft;

import peaksoft.config.Util;
import peaksoft.enums.Gender;
import peaksoft.models.Student;
import peaksoft.servises.StudentService;
import peaksoft.servises.StudentServiceImpl;

import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
        StudentService studentService = new StudentServiceImpl();
        /** connectivety database **/
//        Util.getConnection();
        /** create table **/
//        studentService.createTable();
        /** save student **/
        Student student = new Student("Ilim", (byte) 19);
        studentService.saveStudent(student);
        /** find by id **/
//        studentService.findByID(2L);
        /** find by all **/
//        System.out.println(studentService.findAll());
        /** update **/
//        studentService.updateStudent(2L, new Student("Alibek", (byte) 21));
        /** deleted by id **/
//        studentService.deletedStudent(1L);
        /** sorted by age **/
//        System.out.println(studentService.getAllStudentsSortByAge("descending"));
        /** chek by age **/
//        System.out.println(studentService.checkByAge());
        /** add column **/
//        studentService.addColumnGender();
        /** group gender **/
//        System.out.println(studentService.gruopByGender());
        /** truncate table **/
        studentService.deleteAllStudents();

    }
}
