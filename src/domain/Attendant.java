package domain;

public class Attendant extends Person{
    //fields
    private AttendantRole attendantRole;

    //constructor
    public Attendant(Integer idAttendant, String nameAttendant, AttendantRole attendantRole) {
        super(idAttendant, nameAttendant);
        this.attendantRole = attendantRole;
    }

    //getters and setters
    public AttendantRole getAttendantRole() {
        return attendantRole;
    }

    public void setAttendantRole(AttendantRole attendantRole) {
        this.attendantRole = attendantRole;
    }

}
