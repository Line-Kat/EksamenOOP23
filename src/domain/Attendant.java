package domain;

public class Attendant extends Person{
    //fields
    private AttendantRole attendantRole;
    private String studyProgram;

    //constructor
    public Attendant(Integer idAttendant, String nameAttendant, AttendantRole attendantRole, String styudyProgram) {
        super(idAttendant, nameAttendant);
        this.attendantRole = attendantRole;
        this.studyProgram = styudyProgram;
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
