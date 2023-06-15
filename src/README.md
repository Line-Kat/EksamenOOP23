Welcome to the program EventManager!

This program is for student to register to the graduation ceremony and see who else is attending.
The staff is invited by default, so they are not in the table attendants.

Before starting the program you must run the sql scripts sqlScriptEventDB and sqlScriptUniversityDB to create 
the databases. The scripts contain inserts to fill the tables with some values. Only the database eventDB can 
be edited from the program.

The databases
universityDB with tables
    - staff 
    - staffRole (teachers, program responsible, others)
    - student
    - studyProgram
eventDB with tables
    - attendants (students attending the graduation ceremony)
    - guests

First a general menu will be shown in the console and one of the options are to sign in with name.
The program checks if the name is on the list of students at the school. If it is, a method to show menu for students 
are called with the object of type student as an argument. That way the student don't have to type in information 
about himself/herself again. Another option in the general menu is to print the graduation ceremony program with
general information. When choosing exit, the program stops running.

When the student is logged in there are several options. The student can 
    - register to attend the graduation ceremony with guests. This will update the table attending 
    and add the student, and update the table guests and add the guests.
    - list and search after persons attending the graduation ceremony. When searching for å participant, the method
    uses contains() and will list all persons that contains what the user typed
    - see the graduation ceremony program. Since the method has the student as a parameter, the program shows 
    which study program the student is a part if. This program also contains information on how long the different 
    post on the program takes and the total time of the ceremony
    - add or remove guests
    - remove the registration (removes both the student from the table attendants and the students guests from the
    table guests)   
    - sign out. The student will return to the main menu.

I hope you enjoy the program!



