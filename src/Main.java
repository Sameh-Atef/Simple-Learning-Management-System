import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.out.println("Hello world!");
        //Students.convertData("student-data","txt");
        //Courses.convertData("Coursedata","xml");
        //Students.displayData();
        //Students.displayData("4");
        //Courses.displayData("7");
        //Details.displayDetails("44");
        //System.out.println(Details.getCourses("45"));
        Scanner scanner  = new Scanner(System.in);
        displayHomepage();
        Students.displayData();
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Please Select the required student: ");
        String student_id=scanner.next();
        Details.displayDetails(student_id);

    }
    private static void displayHomepage(){
        final String Name = "Sameh Atef";
        System.out.println("Welcome to LMS");
        System.out.println("Create by: "+"{"+Name +"}");
        System.out.println("====================================================================================");
        System.out.println("Home page");
        System.out.println("====================================================================================");

    }

}