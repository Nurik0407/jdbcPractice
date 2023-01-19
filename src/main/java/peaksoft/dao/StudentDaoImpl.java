package peaksoft.dao;

import peaksoft.config.Util;
import peaksoft.enums.Gender;
import peaksoft.models.Student;

import java.sql.*;
import java.util.*;

public class StudentDaoImpl implements StudentDao {

    private Connection connection;

    public StudentDaoImpl() throws SQLException {
        this.connection = Util.getConnection();
    }

    @Override
    public void createTable() {
        try (Statement statement = connection.createStatement();) {
            statement.execute("create table students(id serial primary key ,name varchar,age int)");
            System.out.println("Successfully crated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveStudent(Student student) {
        String sql = "insert into students(name,age) values(?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setByte(2, student.getAge());
            preparedStatement.execute();
            System.out.println("Successfully saved");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void findByID(Long idStudent) {
        String sql = "select * from students where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, idStudent);
            ResultSet resultSet = preparedStatement.executeQuery();

            Student student = new Student();
            while (resultSet.next()) {
                student.setId(resultSet.getLong(1));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));
                resultSet.close();
                System.out.println(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> findAll() {
        String sql = "select * from students;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(new Student(resultSet.getLong(1), resultSet.getString(2), resultSet.getByte(3)));
            }
            return students;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void updateStudent(Long studentId, Student newStudent) {
        String sql = "update  students " +
                "set name = ?, " +
                "age = ? where id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newStudent.getName());
            preparedStatement.setByte(2, newStudent.getAge());
            preparedStatement.setLong(3, studentId);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deletedStudent(Long studentId) {
        String sql = "Delete from students where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, studentId);
            preparedStatement.executeUpdate();
            System.out.println("Successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Student> getAllStudentsSortByAge(String ascOrDesc) {
        String sql1 = """
                select * from students order by age;
                 """;
        String sql2 = """
                select * from students order by age desc;
                 """;
        String sql = "";
        if (ascOrDesc.toLowerCase().equals("ascending")) {
            sql = sql1;
        } else if (ascOrDesc.toLowerCase().equals("descending")) {
            sql = sql2;
        }
        List<Student> students = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                students.add(new Student(resultSet.getLong(1), resultSet.getString(2), resultSet.getByte(3)));
            }
            return students;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean checkByAge() {
        String query = "select name, age,case when age > 18 then 'true' else 'false' end from students";
        // String query = "select  name,age from students where age > 18 group by name, age";
        boolean isTrue = false;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                resultSet.getString("name");
                resultSet.getByte("age");
                isTrue = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isTrue;
    }

    @Override
    public void addColumnGender() {
        String sql = "alter table students add column gender varchar check ( gender='Female' or gender='Male')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("Column successfully added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Map<Gender, List<Student>> gruopByGender() {
        Map<Gender, List<Student>> result = new HashMap<>();
        List<Student> girls = new ArrayList<>();
        List<Student> boys = new ArrayList<>();
        String sql = """
                select * from students where gender='FEMALE';
                """;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong("id"));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getByte(3));
                girls.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String sql1 = """
                select * from students where gender='MALE';
                """;
        try (Statement statement1 = connection.createStatement()) {
            ResultSet resultSet1 = statement1.executeQuery(sql1);
            while (resultSet1.next()) {
                Student student = new Student();
                student.setId(resultSet1.getLong("id"));
                student.setName(resultSet1.getString(2));
                student.setAge(resultSet1.getByte(3));
                boys.add(student);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        result.put(Gender.FEMALE, girls);
        result.put(Gender.MALE, boys);
        return result;
    }

    @Override
    public void deleteAllStudents() {
        String sql = "truncate students;";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Table successfully cleared");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
