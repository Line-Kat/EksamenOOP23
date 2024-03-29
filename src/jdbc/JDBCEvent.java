package jdbc;

import domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCEvent {
    JDBCOps jdbcOps = new JDBCOps();
    String database = "eventDB";

    //method that deletes student from attending table and the invited guests from the guest table
    public void deleteFromAttendants(Attendant attendant) {
        String deleteFromGuests = "DELETE FROM guests WHERE attendants_idAttendant=?;";
        String deleteFromAttendants = "DELETE FROM attendants WHERE idAttendant=?;";

        try(Connection con = jdbcOps.getConnection(database);
            PreparedStatement stmtGuest = con.prepareStatement(deleteFromGuests);
            PreparedStatement stmtAttendant = con.prepareStatement(deleteFromAttendants)) {

            stmtGuest.setInt(1, attendant.getId());
            stmtGuest.executeUpdate();

            stmtAttendant.setInt(1, attendant.getId());
            stmtAttendant.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //method that returns a list of the students attending the graduation ceremony
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

                Attendant attendant = new Attendant(idAttendant, nameAttendant, "STUDENT", studyProgram);

                listOfStudentAttending.add(attendant);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return listOfStudentAttending;
    }

    //method that returns a list of the guests attending the graduation ceremony
    public List<Guest> listOfGuests() {
        List<Guest> listOfGuests = new ArrayList<>();

        try(Connection con = jdbcOps.getConnection(database);
            Statement stmt = con.createStatement()) {

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

    //method that inserts the student logged in into the attending table
    //if the student is already in the attending table, (s)he won't be registered again
    //the method returns the attendant, so the id can be used as foreign key int the guest table
    public Attendant insertAttendant(Student student) {
        String insertAttendantQuery = "INSERT INTO attendants(nameAttendant, role, studyProgram) VALUES(?, ?, ?);";
        String getIdAttendantQuery = "SELECT MAX(idAttendant) AS maxId FROM attendants;";

        try(Connection con = jdbcOps.getConnection(database);
            PreparedStatement stmt = con.prepareStatement(insertAttendantQuery)) {

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
            stmt.setString(3, student.getStudyProgram());
            stmt.executeUpdate();

            ResultSet resultSet = stmt.executeQuery(getIdAttendantQuery);

            if(resultSet.next()) {
                int idAttendant = resultSet.getInt("maxId");
                String studyProgram = student.getStudyProgram();

                System.out.println(student.getName() + ", you are now registered to join the graduation ceremony");

                return new Attendant(idAttendant, student.getName(), "STUDENT", studyProgram);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //method that deletes a guest from the guest table
    public void deleteGuest(Attendant attendant, String nameGuest) {
        String deleteFromGuestsQuery = "DELETE FROM guests WHERE attendants_idAttendant=? AND nameGuest=?;";

        try(Connection con = jdbcOps.getConnection(database);
            PreparedStatement stmt = con.prepareStatement(deleteFromGuestsQuery)) {

            stmt.setInt(1, attendant.getId());
            stmt.setString(2, nameGuest);
            stmt.executeUpdate();

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    //method that inserts a guest into guest table, if s(he) isn't already in the guest table
    public void insertGuest(Attendant attendant, Guest guest) {
        String insertGuestQuery = "INSERT INTO guests(attendants_idAttendant, nameGuest) VALUES(?, ?);";

        try(Connection con = jdbcOps.getConnection(database);
            PreparedStatement stmt = con.prepareStatement(insertGuestQuery)) {

            List<Guest> listOfGuests = listOfGuests();

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
