import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class runner {
    public static void main(String[] args) throws IOException, ParseException {
        //createJson();
        //System.out.println(getCoursesForStudent("2"));
        //JSONParser parser = new JSONParser();
        //JSONObject studentDetails = (JSONObject) parser.parse(new FileReader("src/data/Student_course_details.json"));
        //JSONArray studentDetails = (JSONArray) parser.parse(new FileReader("src/data/Student_course_details.json"));
        //System.out.println(studentDetails.get("1").getClass());
        //System.out.println(studentDetails.get("10").getClass());
        //System.out.println(getCoursesForStudents("5"));
        //enrollStudent("3","4");
        /*Scanner scanner = new Scanner(System.in);
        try (FileWriter file = new FileWriter("src/data/test.json")) {
            System.out.println("Successfully updated json file");
        } catch (Exception e) {
            System.out.println("Error: " + e);}*/


    }

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
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

    }
    public static ArrayList<Integer> getCoursesForStudent(String studentId) {
        ArrayList<Integer> courses = new ArrayList<>();
        try {
            JSONParser parser = new JSONParser();
            JSONArray studentDetails = (JSONArray) parser.parse(new FileReader("src/data/Student_course_details.json"));
            //JSONObject studentDetails = (JSONObject) parser.parse(new FileReader("src/data/Student_course_details.json"));
            for (Object object : studentDetails) {
                JSONObject jsonObject = (JSONObject) object;
                String id = (String) jsonObject.get("student_id");
                if (id.equals(studentId)) {
                    JSONArray courseArray = (JSONArray) jsonObject.get("courses");
                    for (Object course : courseArray) {
                        courses.add(Integer.parseInt(course.toString()));
                    }

                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return courses;
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
    public static List<String> getCourses (String student_id) throws IOException {
        final File input = new File("src/data/Details.json");
        List<String> output = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = null;
            String[] tokens = new String[0];
            String words = null;
            //String line = StringUtils.EMPTY;
            String course_id;
            //String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
            while ((line = reader.readLine()) != null) {
                if(line.contains(student_id+"\"")){
// note max course student can register in is 6 courses
                    for(int i=0; i<6;i++){
                        /*if(reader.readLine().contains("]"))
                            break;*/
                        course_id = extractValue(reader.readLine());
                        if(course_id=="null")
                            break;
                        output.add(course_id);

                    }
                }

            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return output;

    }
    private static String extractValue(String line) {
        //pattern to parser xml created by trying and error
        Pattern pattern = Pattern.compile("([0-9]|\\d)");
        Matcher matcher = pattern.matcher(line);
        String str = "null";
        if (matcher.find()) {
            //str = null;
            str = matcher.group(1);
            //System.out.println(str);
        }
        //return str;
        return str;
        //return "Not-found";
    }
    public static void enrollStudent(String studentId, String courseId){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("1",1);
        try (FileWriter file = new FileWriter("src/data/test.json")) {
            file.write(jsonObject.toJSONString());
            file.close();
            System.out.println("Successfully created JSON file.");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }


    }


}


