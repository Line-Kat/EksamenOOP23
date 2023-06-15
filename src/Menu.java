import domain.Attendant;
import domain.Person;
import domain.Student;

import java.util.List;
import java.util.Scanner;

public class Menu {
    //creates scanner only once and sends it as argument to different methods
    Scanner scanner = new Scanner(System.in);
    EventManagement eventManagement = new EventManagement();

    public void runProgram() {
        System.out.println("Welcome to EventManagement!");

        boolean keepRunning = true;

        while(keepRunning) {
            printMainMenu();
            String userInput = scanner.nextLine();

            switch(userInput) {
                //sign in user only if s(he) is a student at the school (in the student table)
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
                    break;
                }
                case "2" : {
                    //prints the program for users that are not students (method printProgram with signature with no parameters)
                    eventManagement.printProgram();
                    break;
                }
                case "3": {
                    //exit the program
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

    //method to print out main menu
    private void printMainMenu() {
        System.out.println("Here are your options:");
        System.out.println("1. Sign in");
        System.out.println("2. See the program for the ceremony");
        System.out.println("3. Exit");
    }

    //method to handle menu for user that are logged in as student
    private void studentMenu(Scanner scanner, Student student) {
        System.out.println("Welcome " + student.getName() + "!");
        boolean keepRunning = true;

        while(keepRunning) {
            String userInput = printStudentMenu(scanner);
            switch(userInput) {
                case "1" : {
                    //register student as attendant to the graduation ceremony
                    eventManagement.registerStudent(scanner, student);
                    break;
                }
                case "2" : {
                    //list all student participants
                    List<Attendant> listOfStudentsAttending = eventManagement.listOfStudentsAttending();
                    System.out.println("Here are the names of the students participating at the graduation ceremony:");
                    for(Attendant a : listOfStudentsAttending) {
                        System.out.println(a.getName());
                    }
                    break;
                }
                case "3" : {
                    //list all participants (including program responsible, teachers, students, guests)
                    List<Person> listOfAllAttendants = eventManagement.listOfAllAttendants();
                    System.out.println("Here are the names of all the participants at the graduation ceremony");
                    for(Person p : listOfAllAttendants) {
                        System.out.println(p.getName());
                    }
                    System.out.println();
                    break;
                }
                case "4" : {
                    //list students for å chosen program
                    //1. design 2. health 3. it 4. economy
                    System.out.println("Choose program:");
                    System.out.println("1. Design");
                    System.out.println("2. Health");
                    System.out.println("3. IT");
                    System.out.println("4. Economy");

                    String program = scanner.nextLine();

                    List<Attendant> listOfAllStudentsAttending = eventManagement.listOfStudentsAttending();

                    switch(program) {
                        case "1" : {
                            System.out.println("List of students from the program 'Design':");
                            boolean emptyList = true;
                            for(Attendant a : listOfAllStudentsAttending) {
                                if(a.getStudyProgram().equalsIgnoreCase("design")) {
                                    System.out.println(a.getName());
                                    emptyList = false;
                                }
                            }
                            System.out.println();
                            if(emptyList) {
                                System.out.println("No participants from the program 'Design'");
                                System.out.println();
                            }
                            break;
                        }
                        case "2" : {
                            System.out.println("List of students from the program 'Health'");
                            boolean emptyList = true;
                            for(Attendant a : listOfAllStudentsAttending) {
                                if(a.getStudyProgram().equalsIgnoreCase("health")) {
                                    System.out.println(a.getName());
                                    emptyList = false;
                                }
                            }
                            System.out.println();
                            if(emptyList) {
                                System.out.println("No participants from the program 'Health'");
                                System.out.println();
                            }
                            break;
                        }
                        case "3" : {
                            System.out.println("List of students from the program 'IT'");
                            boolean emptyList = true;
                            for(Attendant a : listOfAllStudentsAttending) {
                                if(a.getStudyProgram().equalsIgnoreCase("it")) {
                                    System.out.println(a.getName());
                                    emptyList = false;
                                }
                            }
                            System.out.println();
                            if(emptyList) {
                                System.out.println("No participants from the program 'IT'");
                                System.out.println();
                            }
                            break;
                        }
                        case "4" : {
                            System.out.println("List of students from the program 'Economy'");
                            boolean emptyList = true;
                            for(Attendant a : listOfAllStudentsAttending) {
                                if(a.getStudyProgram().equalsIgnoreCase("economy")) {
                                    System.out.println(a.getName());
                                    emptyList = false;
                                }
                            }
                            System.out.println();
                            if(emptyList) {
                                System.out.println("No participants from the program 'Economy'");
                                System.out.println();
                            }
                            break;
                        }
                        default : {
                            System.out.println("Invalid input");
                            System.out.println();
                        }
                    }
                    break;
                }
                case "5" : {
                    //search for in participants by name
                    System.out.println("Type the name you're searching for");
                    String name = scanner.nextLine();
                    eventManagement.searchForPersonAttending(name);
                    break;
                }
                case "6" : {
                    //see the program (for users that are students)
                    eventManagement.printProgram(student);
                    break;
                }
                case "7" : {
                    //remove a registration
                    eventManagement.removeRegistration(student);
                    System.out.println("You are now removed from the registration");
                    System.out.println("If you change your mind, feel free to log in register again");
                    keepRunning = false;
                    break;
                }
                case "8" : {
                    //change guests
                    eventManagement.modifyRegistration(scanner, student);
                    break;
                }
                case "9" : {
                    //sign out
                    keepRunning = false;
                    break;
                }
                default : {
                    System.out.println("Invalid input!");
                }
            }
        }
    }

    //method to print menu to users logged in as student, returns a string with the users choice from the menu
    private String printStudentMenu(Scanner scanner) {
            System.out.println("Here are your options:");
            System.out.println("1. Register for the event");
            System.out.println("2. List all students participating in the ceremony");
            System.out.println("3. See all participants");
            System.out.println("4. See participants from one of the programs");
            System.out.println("5. Search for participant by name");
            System.out.println("6. See the program for the ceremony");
            System.out.println("7. Remove registration");
            System.out.println("8. Change guests");
            System.out.println("9. Sign out");

        return scanner.nextLine();
    }
}
