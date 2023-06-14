CREATE DATABASE universityDB;
USE universityDB;

CREATE TABLE studyProgram(
    idStudyProgram INT AUTO_INCREMENT NOT NULL,
    nameProgram VARCHAR(1000),
    PRIMARY KEY (idStudyProgram)
);

CREATE TABLE student(
    idStudent INT AUTO_INCREMENT NOT NULL,
    nameStudent VARCHAR(1000),
    studyProgram_idStudyProgram INT,
    PRIMARY KEY (idStudent),
    FOREIGN KEY (studyProgram_idStudyProgram) REFERENCES studyProgram(idStudyProgram)
);

CREATE TABLE staffRole (
   idStaffRole INT AUTO_INCREMENT NOT NULL,
   role VARCHAR(1000),
   PRIMARY KEY (idStaffRole)
);

CREATE TABLE staff (
    idStaff INT AUTO_INCREMENT NOT NULL,
    nameStaff VARCHAR(1000),
    staffRole_idStaffRole INT,
    studyProgram_idStudyProgram INT,
    PRIMARY KEY (idStaff),
    FOREIGN KEY (studyProgram_idStudyProgram) REFERENCES studyProgram(idStudyProgram),
    FOREIGN KEY (staffRole_idStaffRole) REFERENCES staffRole(idStaffRole)
);

INSERT INTO studyProgram(nameProgram)
VALUES
    ('Design'),
    ('Health'),
    ('IT'),
    ('Economy');

INSERT INTO student(nameStudent, studyProgram_idStudyProgram)
VALUES
    ('Kari', 1),
    ('Sondre', 1),
    ('Peder', 1),
    ('Klara', 1),
    ('Silje', 2),
    ('Olav', 2),
    ('Karoline', 2),
    ('Herman', 2),
    ('Olivia', 3),
    ('Kristian', 3),
    ('Morten', 3),
    ('Marius', 3),
    ('Oda', 4),
    ('Pernille', 4),
    ('Sindre', 4),
    ('Othilie', 4);

INSERT INTO staffRole (role)
VALUES
    ('Teacher'),
    ('Program responsible'),
    ('Other');

INSERT INTO staff(nameStaff, staffRole_idStaffRole, studyProgram_idStudyProgram)
VALUES
    ('Fredrik', 2, 1),
    ('Tove', 2, 2),
    ('Helena', 2, 3),
    ('Kristoffer', 2, 4),
    ('Karoline', 1, 1),
    ('Petrus', 1, 1),
    ('Jakob', 1, 2),
    ('Noah', 1, 2),
    ('Oliver', 1, 3),
    ('Filip', 1, 3),
    ('Emma', 1, 4),
    ('Frida', 1, 4),
    ('Sara', 3, 1),
    ('Sofia', 3, 4);

