import java.util.ArrayList;
import java.util.List;

public class Course {
    String Name;
    String Course_Code;
    String Day;
    int Start_Time;
    int Finish_Time;
    int Max_Student;
    List<Student> Registered_Students;

    public Course(String Name, String Course_Code, String Day, int Start_Time, int Finish_Time, int Max_Student) {
        this.Name = Name;
        this.Course_Code = Course_Code;
        this.Day = Day;
        this.Start_Time = Start_Time;
        this.Finish_Time = Finish_Time;
        this.Max_Student = Max_Student;
        this.Registered_Students = new ArrayList<>();
    }
}