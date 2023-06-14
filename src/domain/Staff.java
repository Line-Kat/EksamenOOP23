package domain;

public class Staff extends Person {
    private String role;

    //constructor
    public Staff(int id, String name, String role) {
        super(id, name);
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
