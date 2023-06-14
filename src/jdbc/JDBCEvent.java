package jdbc;

import domain.Attendant;
import domain.AttendantRole;
import domain.Student;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEvent {
    JDBCOps jdbcOps = new JDBCOps();
    JDBCUniversity jdbcUniversity = new JDBCUniversity();

    public List<Attendant> listOfStudentAttending() {
        List<Attendant> listOfStudentAttending = new ArrayList<>();

        try(Connection con = jdbcOps.getConnection("eventDB"); Statement stmt = con.createStatement()) {
            String getAllStudentAttending = "SELECT * FROM attendants WHERE role='STUDENT'";

            ResultSet resultSet = stmt.executeQuery(getAllStudentAttending);

            while(resultSet.next()) {
                int idAttendant = resultSet.getInt("idAttendant");
                String nameAttendant = resultSet.getString("nameAttendant");

                Attendant attendant = new Attendant(idAttendant, nameAttendant, AttendantRole.STUDENT);

                listOfStudentAttending.add(attendant);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return listOfStudentAttending;
    }
    public Attendant registerAttendant(Student student) {
        String insertAttendantQuery = "INSERT INTO attendants(nameAttendant, role) VALUES(?, ?);";
        String getIdAttendantQuery = "SELECT MAX(idAttendant) AS maxId FROM attendants;";

        try(Connection con = jdbcOps.getConnection("eventDB"); PreparedStatement stmt = con.prepareStatement(insertAttendantQuery)) {

            List<Attendant> listOfAllStudentAttendants = listOfStudentAttending();

            for(Attendant a : listOfAllStudentAttendants) {
                if(student.getName().equalsIgnoreCase(a.getNameAttendant())) {
                    System.out.println("You are already registered");
                    System.out.println("If that's not the case, contact administration");
                    return a;
                }
            }

            stmt.setString(1, student.getName());
            stmt.setString(2, "STUDENT");
            stmt.executeUpdate();

            ResultSet resultSet = stmt.executeQuery(getIdAttendantQuery);

            if(resultSet.next()) {
                int idAttendant = resultSet.getInt("maxId");

                System.out.println(student.getName() + ", you are now registered to join the graduation ceremony");

                return new Attendant(idAttendant, student.getName(), AttendantRole.STUDENT);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
