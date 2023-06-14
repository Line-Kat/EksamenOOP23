package domain;

public class Student extends Person{
    //fields
    private String studyProgram;

    //constructor
    public Student(int id, String name, String studyProgram) {
        super(id, name);
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
