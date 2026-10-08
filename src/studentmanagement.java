import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class studentmanagement {

    static Scanner sc = new Scanner(System.in);

    public static void addstudent() {

        try {
            Connection con = dbconnection.getconnection();

            System.out.println("Enter Id:");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter Name:");
            String name = sc.nextLine();

            System.out.println("Enter Register Number:");
            String regnum = sc.nextLine();

            System.out.println("Enter Department:");
            String dept = sc.nextLine();

            System.out.println("Enter Address:");
            String address = sc.nextLine();

            System.out.println("Enter Mark:");
            double mark = sc.nextDouble();
            sc.nextLine();

            String sql = "INSERT INTO student VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, regnum);
            ps.setString(4, dept);
            ps.setString(5, address);
            ps.setDouble(6, mark);

            ps.executeUpdate();

            System.out.println("Student Added Successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addcourse() {

        try {
            Connection con = dbconnection.getconnection();

            System.out.println("Enter Course Name:");
            String name = sc.nextLine();

            System.out.println("Enter Instructor Name:");
            String instructor = sc.nextLine();

            String sql = "INSERT INTO course(course_name,instructor) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, instructor);

            ps.executeUpdate();

            System.out.println("Course Added Successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enrollstudent() {

        try {
            Connection con = dbconnection.getconnection();

            System.out.println("Enter Student Id:");
            int studentid = sc.nextInt();

            System.out.println("Enter Course Id:");
            int courseid = sc.nextInt();
            sc.nextLine();

            String sql = "INSERT INTO enrollment(student_id,course_id) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentid);
            ps.setInt(2, courseid);

            ps.executeUpdate();

            System.out.println("Student Enrolled Successfully!");

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewcourses() {

        try {
            Connection con = dbconnection.getconnection();

            String sql = "SELECT * FROM course";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("COURSE DETAILS");

            while (rs.next()) {

                System.out.println(
                    rs.getInt("course_id") + "|" +
                    rs.getString("course_name") + "|" +
                    rs.getString("instructor")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewstudentcourses() {

        try {
            Connection con = dbconnection.getconnection();

            System.out.println("Enter Student Id:");
            int studentid = sc.nextInt();
            sc.nextLine();

            String sql =
                "SELECT s.name,c.course_name,c.instructor " +
                "FROM student s " +
                "JOIN enrollment e ON s.id=e.student_id " +
                "JOIN course c ON e.course_id=c.course_id " +
                "WHERE s.id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentid);

            ResultSet rs = ps.executeQuery();

            System.out.println("STUDENT COURSES");

            while (rs.next()) {

                System.out.println(
                    "Student: " + rs.getString("name") +
                    " | Course: " + rs.getString("course_name") +
                    " | Instructor: " + rs.getString("instructor")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeenrollment() {

        try {
            Connection con = dbconnection.getconnection();

            System.out.println("Enter Student Id:");
            int studentid = sc.nextInt();

            System.out.println("Enter Course Id:");
            int courseid = sc.nextInt();
            sc.nextLine();

            String sql =
                "DELETE FROM enrollment WHERE student_id=? AND course_id=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, studentid);
            ps.setInt(2, courseid);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Enrollment Removed Successfully!");
            } else {
                System.out.println("Enrollment Not Found!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchstudent() {

        try {
            Connection con = dbconnection.getconnection();

            System.out.println("Enter Register Number:");
            String regnum = sc.nextLine();

            String sql = "SELECT * FROM student WHERE regnum=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, regnum);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("STUDENT FOUND!");

                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Reg No: " + rs.getString("regnum"));
                System.out.println("Dept: " + rs.getString("dept"));
                System.out.println("Address: " + rs.getString("address"));
                System.out.println("Mark: " + rs.getDouble("mark"));

            } else {

                System.out.println("Student Not Found!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\nCOLLEGE COURSE MANAGEMENT!");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student");
            System.out.println("4. View Courses");
            System.out.println("5. View Student Courses");
            System.out.println("6. Remove Enrollment");
            System.out.println("7. Search Student");
            System.out.println("8. Exit");

            System.out.println("Enter your choice:");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addstudent();
                    break;

                case 2:
                    addcourse();
                    break;

                case 3:
                    enrollstudent();
                    break;

                case 4:
                    viewcourses();
                    break;

                case 5:
                    viewstudentcourses();
                    break;

                case 6:
                    removeenrollment();
                    break;

                case 7:
                    searchstudent();
                    break;

                case 8:
                    System.out.println("Thank you!");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}