package domain;

public class Attendant {
    //fields
    private int idAttendant;
    private String nameAttendant;
    private AttendantRole attendantRole;

    public Attendant(int idAttendant, String nameAttendant, AttendantRole attendantRole) {
        this.idAttendant = idAttendant;
        this.nameAttendant = nameAttendant;
        this.attendantRole = attendantRole;
    }

    //getters and setters
    public int getIdAttendant() {
        return idAttendant;
    }

    public void setIdAttendant(int idAttendant) {
        this.idAttendant = idAttendant;
    }

    public String getNameAttendant() {
        return nameAttendant;
    }

    public void setNameAttendant(String nameAttendant) {
        this.nameAttendant = nameAttendant;
    }

    public AttendantRole getAttendantRole() {
        return attendantRole;
    }

    public void setAttendantRole(AttendantRole attendantRole) {
        this.attendantRole = attendantRole;
    }
}
