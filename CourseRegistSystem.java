import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class CourseRegistSystem {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<Course> courses = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            long a = 20201314006L;students.add(new Student("Mehmet" + i, "Çelek" + i +" ", a + i-1 ));
        }

        courses.add(new Course("Object Oriented Programming", "Acm321", "Monday", 12, 15, 5));
        courses.add(new Course("Advanced Web Design", "Acm365", "Tuesday", 9, 12, 5));
        courses.add(new Course("Scripting Languages", "Acm373", "Thursday", 13, 16, 5));
        courses.add(new Course("Data Mining", "Acm476", "Monday", 10, 13, 5));
        courses.add(new Course("Photography", "Comm211", "Thursday", 15, 18, 5));
        courses.add(new Course("History of Turkish Revolution I", "Htr301", "Wednesday", 9, 12, 5));
        courses.add(new Course("Structural Programming", "Acm222", "Friday", 10, 13, 5));
        courses.add(new Course("History of Turkish Revolution II", "Htr302", "Wednesday", 11, 14, 5));
        courses.add(new Course("Turkish Language II", "Tkl202", "Friday", 9, 12, 5));
        courses.add(new Course("Managerial Accounting", "Bba341", "Tuesday", 16, 19, 5));

        Scanner scanner = new Scanner(System.in);
        Student selectedStudent = null;

        while (true) {
            System.out.println("Registered Students in System:");
            for (Student student : students) {
                System.out.println(student.School_Number + " " + student.Name + " " + student.Surname);
            }

            System.out.println("\nEnter student's school number for course add/drop: ");
            long selectedStudentNumber = scanner.nextLong();

            selectedStudent = null;
            for (Student student : students) {
                if (student.School_Number == selectedStudentNumber) {
                    selectedStudent = student;
                    break;
                }
            }

            if (selectedStudent == null) {
                System.out.println("Undefined School Number. Try again.");
                continue;
            }

            System.out.println("\nThe selected student: " + selectedStudent.Name + " " + selectedStudent.Surname);
            enrollCourse(selectedStudent, courses);

            System.out.println("\nThe selected student's information for course add/drop:");
            System.out.println("Name : " + selectedStudent.Name);
            System.out.println("Surname : " + selectedStudent.Surname);
            System.out.println("School Number : " + selectedStudent.School_Number);
            System.out.println("Selected Courses :");
            for (Course course : selectedStudent.Selected_Courses) {
                System.out.println(course.Course_Code + ". " + course.Name + " - " + course.Day +
                        " between " + course.Start_Time + ":00-" + course.Finish_Time + ":00");
            }

            if (selectedStudent.Selected_Courses.size() >= 5) {
                System.out.println("Student reached the maximum number of courses.");
                System.out.println("Selected Courses :");
                for (Course course : selectedStudent.Selected_Courses) {
                    System.out.println(course.Course_Code + "  " + course.Name + " - " + course.Day +
                            " between " + course.Start_Time + ":00-" + course.Finish_Time + ":00");
                }

                System.out.println("Enter 'd' to drop a Course, 'c' to change the student, 'q' to quit, or 'f' to finish: ");
                String action = scanner.next();

                if (action.equals("f")) {
                    System.out.println("Course registration completed.");
                    break;
                } else if (action.equals("d")) {
                    dropCourse(selectedStudent, scanner);
                } else if (action.equals("c")) {
                    continue;
                } else if (action.equals("q")) {
                    System.out.println("Exiting the program...");
                    System.exit(0);
                } else {
                    System.out.println("Undefined choice. Try again.");
                }
            }
            else {
                System.out.println("Enter 'a' to add a Course, 'd' to drop a Course, 'c' to change the student, 'q' to quit, or 'f' to finish: ");
                String action = scanner.next();

                if (action.equals("f")) {
                    System.out.println("Course registration completed.");
                    break;
                } else if (action.equals("a")) {
                    enrollCourse(selectedStudent, courses);
                } else if (action.equals("d")) {
                    dropCourse(selectedStudent, scanner);
                } else if (action.equals("c")) {
                    continue;
                } else if (action.equals("q")) {
                    System.out.println("Exiting the program...");
                    System.exit(0);
                } else {
                    System.out.println("Undefined choice. Try again.");
                }
            }
        }
    }

    private static void enrollCourse(Student student, List<Course> courses) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (student.Selected_Courses.size() >= 5) {
                System.out.println("Student reached the maximum number of courses.");
                System.out.println("Selected courses :");
                for (Course course : student.Selected_Courses) {
                    System.out.println(course.Course_Code + ". " + course.Name + " - " + course.Day +
                            " between " + course.Start_Time + ":00-" + course.Finish_Time + ":00");
                }

                System.out.println("Enter 'd' to drop a Course or 'f' to finish: ");
                String action = scanner.next();

                if (action.equals("f")) {
                    System.out.println("Course registration completed.");
                    break;
                }

                if (action.equals("d")) {
                    dropCourse(student, scanner);
                } else {
                    System.out.println("Undefined choice. Try again.");
                }
            } else {
                System.out.println("Enter 'a' to add a Course, 'd' to drop a Course, or 'f' to finish: ");
                String action = scanner.next();

                if (action.equals("f")) {
                    System.out.println("Course registration completed.");
                    break;
                }

                if (action.equals("a")) {
                    System.out.println("Course List:");
                    for (Course course : courses) {
                        System.out.println(course.Course_Code + "  " + course.Name + " - " + course.Day +
                                " between " + course.Start_Time + ":00-" + course.Finish_Time + ":00");
                    }

                    System.out.println("Enter Course Code ('f' to finishes selection.): ");
                    String courseCode = scanner.next();
                    if (courseCode.equals("f")) {
                        if (student.Selected_Courses.isEmpty()) {
                            System.out.println("There are no courses registered for the student.");
                        }
                        break;
                    }

                    Course selectedCourse = findCourseByCode(courseCode, courses);
                    if (selectedCourse == null) {
                        System.out.println("Undefined Course Code. Try again.");
                        continue;
                    }

                    boolean timeConflict = false;
                    for (Course enrolledCourse : student.Selected_Courses) {
                        if (enrolledCourse.Day.equals(selectedCourse.Day)) {
                            if ((selectedCourse.Start_Time >= enrolledCourse.Start_Time && selectedCourse.Start_Time < enrolledCourse.Finish_Time) ||
                                    (selectedCourse.Finish_Time > enrolledCourse.Start_Time && selectedCourse.Finish_Time <= enrolledCourse.Finish_Time)) {
                                timeConflict = true;
                                break;
                            }
                        }
                    }

                    if (timeConflict) {
                        System.out.println("The selected course conflict with the student's current courses. Choose another course.");
                        continue;
                    }


                    if (student.Selected_Courses.contains(selectedCourse)) {
                        System.out.println("You have already selected this course. Choose another course.");
                        continue;
                    }
                    if (selectedCourse.Registered_Students.size() >= selectedCourse.Max_Student) {
                        System.out.println("The quota for this course is full. Select another course.");
                        continue;
                    }

                    if (selectedCourse.Registered_Students.size() == 5) {
                        System.out.println("5 students have already chosen this course. Choose another course.");
                        continue;
                    }

                    selectedCourse.Registered_Students.add(student);
                    student.Selected_Courses.add(selectedCourse);

                    System.out.println(selectedCourse.Name + " Course is selected successfully.");
                } else if (action.equals("d")) {
                    dropCourse(student, scanner);
                } else {
                    System.out.println("Undefined Course Code. Try again.");
                }
            }
        }
    }
    private static void dropCourse(Student student, Scanner scanner) {
        if (student.Selected_Courses.size() <= 0 ) {
            System.out.println("Course deletion cannot be done because there is no course.");
            return;
        }

        System.out.println("Selected courses for the student");
        for (Course course : student.Selected_Courses) {
            System.out.println(course.Course_Code + "  " + course.Name + " - " + course.Day +
                    " between " + course.Start_Time + ":00-" + course.Finish_Time + ":00");
        }

        System.out.println("Enter Course Code to drop a Course ('f' finishes selection.): ");
        String courseCode = scanner.next();
        if (courseCode.equals("f")) {
            return;
        }

        Course selectedCourse = findCourseByCode(courseCode, student.Selected_Courses);
        if (selectedCourse == null) {
            System.out.println("No course was found the selected course code. Course deletion failed.");
            return;
        }

        student.Selected_Courses.remove(selectedCourse);
        selectedCourse.Registered_Students.remove(student);

        System.out.println(selectedCourse.Name + " course deleted successfully.");
    }
    private static Course findCourseByCode(String code, List<Course> courses) {
        for (Course course : courses) {
            if (course.Course_Code.equals(code)) {
                return course;
            }
        }
        return null;
    }
}