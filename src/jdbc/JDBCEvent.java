package jdbc;

import domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEvent {
    JDBCOps jdbcOps = new JDBCOps();
    String database = "eventDB";

    public void updateAttendants(Attendant attendant) {

    }

    public List<Attendant> listOfStudentAttending() {
        List<Attendant> listOfStudentAttending = new ArrayList<>();

        try(Connection con = jdbcOps.getConnection(database);
            Statement stmt = con.createStatement()) {

            String getAllStudentAttendingQuery = "SELECT * FROM attendants WHERE role='STUDENT'";

            ResultSet resultSet = stmt.executeQuery(getAllStudentAttendingQuery);

            while(resultSet.next()) {
                int idAttendant = resultSet.getInt("idAttendant");
                String nameAttendant = resultSet.getString("nameAttendant");
                String studyProgram = resultSet.getString("studyProgram");

                Attendant attendant = new Attendant(idAttendant, nameAttendant, AttendantRole.STUDENT, studyProgram);

                listOfStudentAttending.add(attendant);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return listOfStudentAttending;
    }

    public List<Guest> listOGuests() {
        List<Guest> listOfGuests = new ArrayList<>();

        try(Connection con = jdbcOps.getConnection(database); Statement stmt = con.createStatement()) {
            String getGuestsQuery = "SELECT * FROM guests;";

            ResultSet resultSet = stmt.executeQuery(getGuestsQuery);

            while(resultSet.next()) {
                int idGuest = resultSet.getInt("idGuest");
                String nameGuest = resultSet.getString("nameGuest");

                Guest guest = new Guest(idGuest, nameGuest);

                listOfGuests.add(guest);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return listOfGuests;
    }
    public Attendant insertAttendant(Student student) {
        String insertAttendantQuery = "INSERT INTO attendants(nameAttendant, role) VALUES(?, ?);";
        String getIdAttendantQuery = "SELECT MAX(idAttendant) AS maxId FROM attendants;";

        try(Connection con = jdbcOps.getConnection(database); PreparedStatement stmt = con.prepareStatement(insertAttendantQuery)) {

            List<Attendant> listOfAllStudentAttendants = listOfStudentAttending();

            for(Attendant a : listOfAllStudentAttendants) {
                if(student.getName().equalsIgnoreCase(a.getName())) {
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
                String studyProgram = resultSet.getString("studyProgram");

                System.out.println(student.getName() + ", you are now registered to join the graduation ceremony");

                return new Attendant(idAttendant, student.getName(), AttendantRole.STUDENT, studyProgram);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertGuest(Attendant attendant, Guest guest) {
        String insertGuestQuery = "INSERT INTO guests(attendants_idAttendant, nameGuest) VALUES(?, ?);";

        try(Connection con = jdbcOps.getConnection(database); PreparedStatement stmt = con.prepareStatement(insertGuestQuery)) {
            List<Guest> listOfGuests = listOGuests();

            for(Guest g : listOfGuests) {
                if(guest.getName().equalsIgnoreCase(g.getName())) {
                    System.out.println("The guest is already registered");
                    System.out.println("If that's not the case, contact administration");
                    return;
                }
            }

            stmt.setInt(1, attendant.getId());
            stmt.setString(2, guest.getName());
            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
