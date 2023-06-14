package domain;

public class Staff extends Person {
    private Role role;

    //constructor
    public Staff(int id, String name, Role role) {
        super(id, name);
        this.role = role;
    }

    //getters and setters
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
