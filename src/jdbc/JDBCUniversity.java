package jdbc;

import domain.Student;
import domain.StudyProgram;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCUniversity {

    JDBCOps jdbcOps = new JDBCOps();
    public List<Student> getAllStudents() {
        List<Student> listOfAllStudents = new ArrayList<>();
        try(Connection con = jdbcOps.getConnection("universityDB"); Statement stmt = con.createStatement()) {
            String getAllStudentsQuery = "SELECT * FROM student JOIN studyProgram ON studyProgram_idStudyProgram = idStudyProgram;";

            ResultSet resultSet = stmt.executeQuery(getAllStudentsQuery);

            while(resultSet.next()) {
                int idStudent = resultSet.getInt("idStudent");
                String nameStudent = resultSet.getString("nameStudent");
                String studyProgram = resultSet.getString("nameProgram");

                Student student = new Student(idStudent, nameStudent, studyProgram);

                listOfAllStudents.add(student);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return listOfAllStudents;
    }

    public List<StudyProgram> getStudyPrograms() {
        List<StudyProgram> listOfStudyPrograms = new ArrayList<>();

        try(Connection con = jdbcOps.getConnection("universityDB"); Statement stmt = con.createStatement()) {
            String getStudyProgramsQuery = "SELECT * FROM studyProgram JOIN staff ON idStudyProgram = studyProgram_idStudyProgram WHERE staffRole_idStaffRole=2;";

            ResultSet resultSet = stmt.executeQuery(getStudyProgramsQuery);

            while(resultSet.next()) {
                int idStudyProgram = resultSet.getInt("idStudyProgram");
                String nameProgram = resultSet.getString("nameProgram");
                String programResponsible = resultSet.getString("nameStaff");

                StudyProgram studyProgram = new StudyProgram(idStudyProgram, nameProgram, programResponsible);

                listOfStudyPrograms.add(studyProgram);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return listOfStudyPrograms;
    }

}
