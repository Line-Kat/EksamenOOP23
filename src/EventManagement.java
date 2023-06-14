import domain.Student;
import jdbc.JDBCUniversity;

import java.util.List;

public class EventManagement {
    JDBCUniversity jdbcUniversity = new JDBCUniversity();
    public List<Student> listOfAllStudents() {
        return jdbcUniversity.listOfAllStudents();
    }

}
