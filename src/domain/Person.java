package domain;

public class Person {
    //fields
    private Integer id;
    private String name;

    //constructor
    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
