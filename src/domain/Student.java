package domain;

public class Student extends Person{
    //fields
    private String studyProgram;

    //constructor
    public Student(Integer idStudent, String nameStudent, String studyProgram) {
        super(idStudent, nameStudent);
        this.studyProgram = studyProgram;
    }

    //getters and setters
    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }
}
