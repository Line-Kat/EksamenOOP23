package domain;

public class Staff extends Person {
    private String role;

    //constructor
    public Staff(Integer idStaff, String nameStaff, String role) {
        super(idStaff, nameStaff);
        this.role = role;
    }

    //getters and setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
