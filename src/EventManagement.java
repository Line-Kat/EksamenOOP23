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
        int totalTime = 0;
        System.out.println("**MENU FOR THE GRADUATION CEREMONY**");
        System.out.println("Start time kl. 13.00");
        System.out.println("Introduction");
        System.out.println("Speech from the program responsible of every program");
        System.out.println("Closing remark");
    }

}
