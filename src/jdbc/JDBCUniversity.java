package jdbc;

import domain.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCUniversity {
    JDBCOps jdbcOps = new JDBCOps();
    JDBCEvent jdbcEvent = new JDBCEvent();
    String database = "universityDB";

    //method that returns a list of all students from the table student
    public List<Student> getAllStudents() {
        List<Student> listOfAllStudents = new ArrayList<>();
        try(Connection con = jdbcOps.getConnection(database);
            Statement stmt = con.createStatement()) {

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

    //method that returns a list of the programs with the program responsible
    public List<StudyProgram> getStudyPrograms() {
        List<StudyProgram> listOfStudyPrograms = new ArrayList<>();

        try(Connection con = jdbcOps.getConnection(database);
            Statement stmt = con.createStatement()) {

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

    //method that returns a list of all the attendants (students, guests, teachers, program responsible)
    public List<Person> listOfAllAttendants() {
        List<Person> listOfAllAttendants = new ArrayList<>();

        try(Connection con = jdbcOps.getConnection(database);
            Statement stmt = con.createStatement()) {

            String getAllStaffQuery = "SELECT * FROM staff;";

            ResultSet resultSet = stmt.executeQuery(getAllStaffQuery);

            while(resultSet.next()) {
                int idPerson = resultSet.getInt("idStaff");
                String namePerson = resultSet.getString("nameStaff");

                Person person = new Person(idPerson, namePerson);

                listOfAllAttendants.add(person);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        List<Attendant> listOfStudentsAttending = jdbcEvent.listOfStudentAttending();
        for(Person p : listOfStudentsAttending) {
            int idPerson = p.getId();
            String namePerson = p.getName();

            Person person = new Person(idPerson, namePerson);

            listOfAllAttendants.add(person);
        }

        List<Guest> listGuests = jdbcEvent.listOfGuests();
        for(Guest g : listGuests) {
            int idPerson = g.getId();
            String namePerson = g.getName();

            Person person = new Person(idPerson, namePerson);
            listOfAllAttendants.add(person);
        }

        return listOfAllAttendants;
    }
}
