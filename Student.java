import java.util.ArrayList;
import java.util.List;

public class Student {
    String Name;
    String Surname;
    long School_Number;
    List<Course> Selected_Courses;

    public Student(String Name, String Surname, long School_Number) {
        this.Name = Name;
        this.Surname = Surname;
        this.School_Number = School_Number;
        this.Selected_Courses = new ArrayList<>();
    }
}