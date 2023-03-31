import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static int  studentId = 0;
    private static Scanner scanner = null;
    //private static Scanner Choice = null;
    static EnrollmentManger Enrollment = null;
    private static String choice = null;
    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        //scanner = new Scanner(System.in);
        //Enrollment = new EnrollmentManger();
        //System.out.println("Hello world!");
        //Students.convertData("student-data","txt");
        //Courses.convertData("Coursedata","xml");
        //Students.displayData();
        //Students.displayData("4");
        //Courses.displayData3("7");
        //Details.displayDetails("44");
        //System.out.println(Details.getCourses("45"));
        //Details.createJson();
        try{
        while (true){
        displayHomepage();
        scanner = new Scanner(System.in);
        //Choice = new Scanner(System.in);
        Enrollment = new EnrollmentManger();

        switch (choice) {
            case "a":
                displayEnrollment(studentId);
                break;
            case "d":
                displayunEnrollmentpage(studentId);
                break;
            case "r":
                displayReplacementpage(studentId);
                break;
            case "b":
                //displayHomepage();
                break;
            default:
                System.out.println("Wrong choice! please enter a valid choice");
                //displayChoice();
        }

    }}catch (Exception e) {
        displayHomepage();
        }
    }


    private static void displayHomepage() throws IOException, ParseException {
        scanner  = new Scanner(System.in);
        displayHomepageHeader();
        Students.displayData();
        System.out.println("------------------------------------------------------------------------------------");
        System.out.println("Please Select the required student: ");
        try {
            studentId = Integer.parseInt(scanner.next());
            Details.displayStudent(studentId);
            Details.displayCourse(studentId);
            displayChoice();

            //displayEnrollment(studentId);
            //Scanner scanner1 = new Scanner(System.in)
        }catch (Exception e){
            System.out.println("The input you have provided is invalid, please enter a valid input");
            displayHomepage();


        }
    }
    private static void displayChoice(){
        scanner = new Scanner(System.in);
        System.out.println("Please choose from the following:");
        System.out.println("a - Enroll in a course");
        System.out.println("d - Unenrollfrom an existing course");
        System.out.println("r - Replacing an existing course");
        System.out.println("b - Back to the main page");
        System.out.println("please select the required action: ");
        choice = scanner.next();
    }


    private static void displayHomepageHeader(){
        final  String Name = "Sameh Atef";
        System.out.println("Welcome to LMS");
        System.out.println("Create by: "+"{"+Name +"}");
        System.out.println("====================================================================================");
        System.out.println("Home page");
        System.out.println("====================================================================================");

    }

    private static void displayEnrollmentHeader(){
        System.out.println("Enrollment");
        //System.out.println("====================================================================================");
    }
    private static void displayEnrollment(int studentId) throws IOException, ParseException {
        //Enrollment = new EnrollmentManger();
        displayEnrollmentHeader();
        Details.displayCourse(studentId);
        System.out.println("Enter the course id that you want to enroll the student to");
        System.out.println("Enter b to go back to the home page");
        //String input = scanner.next();
        try {
            String input = scanner.next();
            //int courseId = Integer.parseInt(input);
            if(String.valueOf(input).equals("b")){

                //displayHomepage();
            }
            //Character.isLetter(input);
            else if(Integer.valueOf(input)>0){
                // enroll student at  this course
                Enrollment.enrollStudent(studentId,Integer.valueOf(input));
                displayEnrollment(studentId);
            }
        } catch(Exception e){
            System.out.println("The input you have provided is invalid, please enter a valid input");
            displayEnrollment(studentId);
        }

    }

    private static void displayunEnrollmentHeader(){
        System.out.println("UnEnrollment ");
        //System.out.println("====================================================================================");
    }
    private static void displayReplacmentHeader(){
        System.out.println("Replacement");
        //System.out.println("====================================================================================");
    }

    private static void displayunEnrollmentpage(int studentId) throws IOException, ParseException {
        displayunEnrollmentHeader();
        Details.displayCourse(studentId);
        System.out.println("Enter the course id that you want to unenroll the student to");
        System.out.println("Enter b to go back to the home page");
        //String input = scanner.next();
        try {
            String input = scanner.next();
            //int courseId = Integer.parseInt(input);
            if(String.valueOf(input).equals("b")){
                //displayHomepage();
            }
            //Character.isLetter(input);
            else if(Integer.valueOf(input)>0){
                // enroll student at  this course
                Enrollment.unenrollStudent(studentId,Integer.valueOf(input));
                displayunEnrollmentpage(studentId);
            }
        } catch(Exception e){
            System.out.println("Faild to unenroll: The student as only one or no courses to unenroll from");
            displayunEnrollmentpage(studentId);
        }


    }
    private static void displayReplacementpage(int studentId) throws IOException, ParseException {
        displayReplacmentHeader();
        Details.displayCourse(studentId);
        System.out.println("Enter the course id that you want to replace the student to");
        System.out.println("Enter b to go back to the home page");

        //String input = scanner.next();
        try {
            String input = scanner.next();
            //int new_course_id = scanner.nextInt();

            //int courseId = Integer.parseInt(input);
            if(String.valueOf(input).equals("b")){
                //displayHomepage();
            }

            //Character.isLetter(input);
            else if(Integer.valueOf(input)>0){

                System.out.println("Enter the new course id that you want to replace to");
                int new_course_id = scanner.nextInt();

                // enroll student at  this course
                Enrollment.replaceStudent(studentId,Integer.valueOf(input),new_course_id);
                displayReplacementpage(studentId);
            }
        } catch(Exception e){
            System.out.println("Faild to replace as the student hasn't enrolled in any course yet or already enroll in this course");
            displayReplacementpage(studentId);;
        }

    }

}