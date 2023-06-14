import domain.*;
import jdbc.JDBCEvent;
import jdbc.JDBCUniversity;

import java.util.List;
import java.util.Scanner;

public class EventManagement {
    JDBCUniversity jdbcUniversity = new JDBCUniversity();
    JDBCEvent jdbcEvent = new JDBCEvent();
    public List<Student> listOfAllStudents() {
        return jdbcUniversity.getAllStudents();
    }
    public List<StudyProgram> listOfStudyPrograms() {
        return jdbcUniversity.getStudyPrograms();
    }
    public List<Attendant> listOfStudentsAttending() {
        return jdbcEvent.listOfStudentAttending();
    }

    public void searchForPersonAttending(String inputName) {
        List<Person> listOfAllAttending = jdbcUniversity.listOfAllAttendants();

        System.out.println("Your search resulted in: ");
        for(Person p : listOfAllAttending) {
            String name = inputName.toLowerCase();
            if(p.getName().toLowerCase().contains(name)) {
                System.out.println(p.getName());
            }
        }
        System.out.println();
    }
    public List<Person> listOfAllAttendants() {
        return jdbcUniversity.listOfAllAttendants();
    }

    public void removeRegistration(Student student) {

        List<Attendant> listAllStudentsAttending = jdbcEvent.listOfStudentAttending();
        for(Attendant a : listAllStudentsAttending) {
            if(a.getName().equalsIgnoreCase(student.getName())) {
                jdbcEvent.deleteFromAttendants(a);
                return;
            }
        }


        System.out.println("Invalid input!");

    }
    //Program printed for students
    public void printProgram(Student student) {
        System.out.println("**PROGRAM FOR THE GRADUATION CEREMONY**");
        System.out.println("Start time kl. 13.00");
        System.out.println("Introduction");
        System.out.println("You are a part of the study program " + student.getStudyProgram());
        int totalTime = 30;

        List<StudyProgram> listOfStudyPrograms = listOfStudyPrograms();
        List<Student> listOfAllStudents = listOfAllStudents();

        for(StudyProgram p : listOfStudyPrograms) {
            int numberOfStudents = 0;
            System.out.println();
            System.out.println("Program: " + p.getNameProgram());

            for(Student s : listOfAllStudents) {
                if(s.getStudyProgram().equals(p.getNameProgram())) {
                    numberOfStudents++;
                }
            }

            int programTime = 1 + (numberOfStudents/5);

            System.out.println("Speech from the program responsible " + p.getProgramResponsible() + " (" + programTime + " minutes)");
            totalTime += programTime;
            System.out.println();
            System.out.println("5 minutes break");
            totalTime += 5;
        }

        System.out.println();
        System.out.println("Closing remark");
        totalTime += 15;
        System.out.println();

        System.out.println("The ceremony will take " + totalTime + " minutes");
        System.out.println();
    }

    //Program printed for users that are not registered as a student
    public void printProgram() {
        System.out.println("**MENU FOR THE GRADUATION CEREMONY**");
        System.out.println("Start time kl. 13.00");
        System.out.println("Introduction");
        System.out.println("Speech from the program responsible of every program");
        System.out.println("Closing remark");
        System.out.println();
    }

    public void registerStudent(Scanner scanner, Student student) {
        Attendant attendant = jdbcEvent.insertAttendant(student);
        System.out.println("You may invite four guests");
        boolean keepRunning = true;


        while(keepRunning) {
            System.out.println("Do you want to invite a guest? (Y/N)");
            String userInput = scanner.nextLine();


            switch (userInput.toLowerCase()) {
                case "y" : {
                    addGuest(scanner, attendant);
                    break;
                }
                case "n" : {
                    keepRunning = false;
                    break;
                }
                default : {
                    System.out.println("Invalid input");
                }
            }
        }
    }

    public void addGuest(Scanner scanner, Attendant attendant) {
        System.out.println("Type the name of the guest you want to invite");
        String guestName = scanner.nextLine();
        Guest guest = new Guest(null, guestName);
        jdbcEvent.insertGuest(attendant, guest);
    }

}
