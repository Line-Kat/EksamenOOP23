package domain;

public class Student extends Person{
    //fields
    private StudyProgram studyProgram;

    //constructor
    public Student(int id, String name, StudyProgram studyProgram) {
        super(id, name);
        this.studyProgram = studyProgram;
    }

    //getters and setters
    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }
}
