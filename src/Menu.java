import domain.Student;
import domain.StudyProgram;
import jdbc.JDBCOps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    EventManagement eventManagement = new EventManagement();

    public void runProgram() {
        System.out.println("Welcome to EventManagement!");

        boolean keepRunning = true;

        while(keepRunning) {
            printMainMenu();
            String userInput = scanner.nextLine();

            switch(userInput) {
                //sign in
                case "1" : {
                    System.out.println("Name:");
                    userInput = scanner.nextLine();
                    List<Student> listOfAllStudents = eventManagement.listOfAllStudents();
                    boolean notOnTheList = true;

                    for(Student s : listOfAllStudents) {
                        if(userInput.equalsIgnoreCase(s.getName())) {
                            studentMenu(scanner, s);
                            notOnTheList = false;
                            break;
                        }
                    }
                    if(notOnTheList){
                        System.out.println("To sign in you must be a student at this school");
                        System.out.println("If you are and didn't get to sign in, please contact the administration");
                        System.out.println();
                        break;
                    }
                }
                case "2" : {
                    eventManagement.printProgram();
                    break;
                }
                case "3": {
                    //exit
                    System.out.println("Thank you for using EventManager");
                    keepRunning = false;
                    break;
                }
                default : {
                    System.out.println("Invalid input!\nChoose a number from the option list");
                }
            }
        }
    }

    private void printMainMenu() {
        System.out.println("Here are your options:");
        System.out.println("1. Sign in");
        System.out.println("2. See the program for the ceremony");
        System.out.println("3. Exit");
    }

    private void studentMenu(Scanner scanner, Student student) {
        System.out.println("Welcome " + student.getName() + "!");
        boolean keepRunning = true;

            while(keepRunning) {
                String userInput = printStudentMenu(scanner);
                switch(userInput) {
                    case "1" : {
                        //register
                        eventManagement.registerStudent(student);
                        break;
                    }
                    case "2" : {
                        //list all participants
                        break;
                    }
                    case "3" : {
                        //list participants for å chosen program
                        //1. design 2. health 3. it 4. economy
                        break;
                    }
                    case "4" : {
                        //search for participant by name
                        break;
                    }
                    case "5" : {
                        //see the program
                        break;
                    }
                    case "6" : {
                        keepRunning = false;
                    }
                    default : {
                        System.out.println("Invalid input!");
                    }
                }
            }
    }
    private String printStudentMenu(Scanner scanner) {
            System.out.println("Here are your options:");
            System.out.println("1. Register for the event");
            System.out.println("2. See all participants");
            System.out.println("3. See participants from one of the programs");
            System.out.println("4. Search for participant by name");
            System.out.println("5. See the program for the ceremony");
            System.out.println("6. Go back to main menu");

        return scanner.nextLine();

        //TODO switch
        //TODO create db
    }
}
