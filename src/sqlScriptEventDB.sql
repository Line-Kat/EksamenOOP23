CREATE DATABASE eventDB;
USE eventDB;

CREATE TABLE attendants (
    idAttendant INT AUTO_INCREMENT NOT NULL,
    nameAttendant VARCHAR(1000),
    role enum('STUDENT', 'PROGRAM_RESPONSIBLE', 'TEACHER'),
    PRIMARY KEY (idAttendant)
);

CREATE TABLE guests (
    idGuest INT AUTO_INCREMENT NOT NULL,
    attendants_idAttendant INT,
    nameGuest VARCHAR(1000),
    PRIMARY KEY (idGuest),
    FOREIGN KEY (attendants_idAttendant) REFERENCES attendants(idAttendant)
);

INSERT INTO attendants(nameAttendant, role)
VALUES
    ('Constantine', 'STUDENT'),
    ('Pål', 'STUDENT'),
    ('Leah', 'PROGRAM_RESPONSIBLE');

INSERT INTO guests(attendants_idAttendant, nameGuest)
VALUES
    (1, 'Selma'),
    (1, 'Nora'),
    (3, 'Anna');