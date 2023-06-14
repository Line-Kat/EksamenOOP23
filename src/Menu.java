import domain.Student;

import java.util.List;
import java.util.Scanner;

public class Menu {

    public void runProgram() {
        Scanner scanner = new Scanner(System.in);
        EventManagement eventManagement = new EventManagement();

        System.out.println("Welcome! Here are your options:");
        printMainMenu();

        String userInput = scanner.nextLine();
        boolean keepRunning = true;

        while(keepRunning) {
            switch(userInput) {
                //sign in
                case "1" : {
                    System.out.println("Name:");
                    userInput = scanner.nextLine();
                    List<Student> listOfAllStudents = eventManagement.listOfAllStudents();
                    boolean notOnTheList = true;

                    for(Student s : listOfAllStudents) {
                        if(userInput.equalsIgnoreCase(s.getName())) {
                            //TODO print student menu and call method that handles the menu
                            notOnTheList = false;
                            break;
                        }
                    }
                    if(notOnTheList){
                        System.out.println("To sign in you must be a student at this school");
                        System.out.println("If you are and didn't get to sign in, please contact the administration");
                        return;
                    }
                }
            }
        }
    }

    private void printMainMenu() {
        System.out.println("1. Sign in");
        System.out.println("2. See overall program");
        System.out.println("3. Exit");
    }
}
