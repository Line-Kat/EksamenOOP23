package jdbc;

import domain.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCUniversity {

    JDBCOps jdbcOps = new JDBCOps();
    public List<Student> listOfAllStudents() {
        List<Student> listOfAllStudents = new ArrayList<>();
        try(Connection con = jdbcOps.getConnection("universityDB"); Statement stmt = con.createStatement()) {
            String getAllStudentsQuery = "SELECT * FROM student JOIN studyProgram ON studyProgram_idStudyProgram = idStudyProgram;";

            ResultSet resultSet = stmt.executeQuery(getAllStudentsQuery);

            while(resultSet.next()) {
                int idStudent = resultSet.getInt("idStudent");
                String name = resultSet.getString("nameStudent");
                String studyProgram = resultSet.getString("nameProgram");

                Student student = new Student(idStudent, name, studyProgram);

                listOfAllStudents.add(student);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return listOfAllStudents;
    }

}
