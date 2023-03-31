import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Details {
    public static void createJson() {
        // Create JSON object
        JSONObject studentDetails = new JSONObject();

        JSONArray courses1 = new JSONArray();
        courses1.add(1);
        courses1.add(2);
        courses1.add(3);
        courses1.add(4);

        JSONArray courses2 = new JSONArray();
        courses2.add(2);
        courses2.add(4);
        courses2.add(6);

        JSONArray courses3 = new JSONArray();
        courses3.add(1);
        courses3.add(3);
        courses3.add(5);

        studentDetails.put("1", courses1);
        studentDetails.put("2", courses2);
        studentDetails.put("3", courses3);

        try (FileWriter file = new FileWriter("src/data/Student_course_details.json")) {
            file.write(studentDetails.toJSONString());
            System.out.println("Successfully created JSON file.");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }


    public static void displayStudent(int student_id) throws IOException, ParseException {
        displayHeader();
        if(isValidStudentId(student_id))
        Students.displayData(String.valueOf(student_id));
    }
    public static void displayCourse(int student_id) throws IOException, ParseException {
        try {
        displayDash();
        Courses.displayHeader1();
        if(isValidStudentId(student_id)){
        if(getCoursesForStudents(String.valueOf(student_id)).size()==0)
            System.out.println("This student hasn't enrolled in any courses");
        else
        {
        for(String course_id:getCoursesForStudents(String.valueOf(student_id))){
             Courses.displayData(String.valueOf(course_id));
             //System.out.println(course_id);
             }
        }
        }
        displayDash();
        }catch(Exception e){
        System.out.println("The input you have provided is invalid, please enter a valid input");
        displayDash();
        }
    }
    //get data from json provide student id and we get courses id
    private static void displayHeader(){
        System.out.println("===============================================");
        System.out.println("Student Details page");
        System.out.println("===============================================");
    }
    private static void displayDash(){
        System.out.println("------------------------------------------------------------------------------------");
    }
    private static boolean isEmpty(List<String>output){
        if(output.isEmpty())
            return true;
        else
            return false;
    }
    public static List<String> getCoursesForStudents(String studentId) throws IOException, ParseException {
        List<String>courses = new ArrayList<>();
        try{
            JSONParser parser = new JSONParser();
            JSONObject studentDetails= (JSONObject) parser.parse(new FileReader("src/data/Student_course_details.json"));
            JSONArray courseArray = (JSONArray) studentDetails.get(studentId);
            if(courseArray!=null){
                for (Object course : courseArray) {
                    courses.add(course.toString());
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e);
        }
        return courses;
    }

    private static boolean isValid(String student_id) {
        try {
            int number = Integer.parseInt(student_id);
            if (number > 100 | number < 0)
                return false;

        }catch (Exception e ){
            System.out.println("Invalid student id");
        }
        return true;
    }
    private static boolean isValidCourseId(int courseId) {
        boolean valid = false;
        // Validation logic for course id
        if(courseId>0 && courseId<=17)
            valid=true;
        // Return true if the course id is valid, false otherwise
        return valid;
    }
    private static boolean isValidStudentId(int studentId) {
        boolean valid = false;
        // Validation logic for student id
        if(studentId>0 && studentId<=100) {
            valid=true;
        }
        // Return true if the student id is valid, false otherwise
        return valid;
    }

    public static void main(String args[]) throws IOException, ParseException {
        //System.out.println(getCoursesForStudents("70").size());
        //displayCourse(70);

    }






}

