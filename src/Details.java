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
        courses3.add(3);

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


    public static void displayDetails(String student_id) throws IOException, ParseException {
        displayHeader();
        if(isValid(student_id)){
        Students.displayData(student_id);

        displayDash();
        Courses.displayHeader1();
        if(isEmpty(getCoursesForStudents(student_id)))
            System.out.println("This student hasn't enrolled in any courses");
        else
        {
        for(String course_id:getCoursesForStudents(student_id)){
             Courses.displayData(String.valueOf(course_id));
             //System.out.println(course_id);
             }
        }}
        else
        System.out.println("Invalid Student ID");
        displayDash();

    }
    //get data from json provide student id and we get courses id
    private static void displayHeader(){
        System.out.println("===============================================");
        System.out.println("Student Details page");
        System.out.println("===============================================");
    }
    private static void displayDash(){
        System.out.println("-------------------------------------------------");
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
        //courses.add(String.valueOf(family)) ;
        //String id = (String) studentDetails.get(studentId);
        return courses;
    }

    public static void enrollStudent(String student_id, String course_id){



    }
    private static boolean isValid(String student_id){
        int number = Integer.parseInt(student_id);
        if(number>100 | number<0)
             return false;
         else
            return true;
    }





}

