import domain.Student;
import domain.StudyProgram;
import jdbc.JDBCUniversity;

import java.util.List;

public class EventManagement {
    JDBCUniversity jdbcUniversity = new JDBCUniversity();
    public List<Student> listOfAllStudents() {
        return jdbcUniversity.getAllStudents();
    }
    public List<StudyProgram> listOfStudyPrograms() {
        return jdbcUniversity.getStudyPrograms();
    }
    public void printProgram() {
        System.out.println("**PROGRAM FOR THE GRADUATION CEREMONY**");
        System.out.println("Start time kl. 13.00");
        System.out.println("Introduction");
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

    //TODO skrive ut meny når bruker skriver ut (skal stå i programmet)
    public void printProgram(Student s) {
        int totalTime = 0;
        System.out.println("**MENU FOR THE GRADUATION CEREMONY**");
        System.out.println("Start time kl. 13.00");
        System.out.println("Introduction");
        totalTime += 30;
        System.out.println("You're registered as a part of the program " + s.getStudyProgram());

        List<StudyProgram> listOfStudyPrograms = listOfStudyPrograms();

        for (StudyProgram p : listOfStudyPrograms) {
            System.out.println("Program: " + p.getNameProgram());
            System.out.println("Speech from the program responsible " + p.getProgramResponsible());
            totalTime += 1;
            //TODO legge til 1 min for hver 5.student
            System.out.println("5 minutes break");
            totalTime += 5;
        }

        System.out.println("Closing remark");
        totalTime += 15;

        System.out.println("The ceremony will take " + totalTime + "minutes");
        //TODO må ha kontakt med tabellen studyprogram(+ staff) for å printe ut navn på programmet og studieansvarlig


    }

}
