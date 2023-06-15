package domain;

public class Attendant extends Person{
    //fields
    private String attendantRole; //student, teacher or program responsible
    private String studyProgram;

    //constructor
    public Attendant(Integer idAttendant, String nameAttendant, String attendantRole, String studyProgram) {
        super(idAttendant, nameAttendant);
        this.attendantRole = attendantRole;
        this.studyProgram = studyProgram;
    }

    //getters and setters
    public AttendantRole getAttendantRole() {
        return attendantRole;
    }

    public void setAttendantRole(AttendantRole attendantRole) {
        this.attendantRole = attendantRole;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }
}
