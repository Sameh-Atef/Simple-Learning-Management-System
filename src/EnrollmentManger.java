/*This function will take the student id and course id and then save this in the JSON file in his/her record if exist, and add a new record in the JSON if not exist.

        Please take care of the following:

        The student has a maximum of 6 courses to enroll in, if the student has more than 6 courses, an Exception will occur.
        The student can't enroll in the same course more than one time
        Validation must be done on the student and course ids
        in java

        Here's an example implementation in Java:

        java*/
        import java.io.*;
        //import org.json.*;
        import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;
        import org.json.simple.parser.ParseException;

public class EnrollmentManger {
    
    private static final int MAX_COURSES = 6;
    private static final int MIN_COURSES = 1;
    private JSONObject data;

    public EnrollmentManger() throws IOException {
        // Load the existing data from the JSON file
        File file = new File("src/data/test.json");
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                //char[] chars = new char[(int) file.length()];
                //reader.read(chars);
                //String json = new String(chars);
                JSONParser parser = new JSONParser();
                data = (JSONObject) parser.parse(reader);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } else {
            data = new JSONObject();
        }
    }

    public void enrollStudent(int studentId, int courseId) throws IOException {
        // Check if the student id and course id are valid
        if (!isValidStudentId(studentId)) {
            throw new IllegalArgumentException("Invalid student id");
        }
        if (!isValidCourseId(courseId)) {
            throw new IllegalArgumentException("Invalid course id");
        }
        JSONArray courses = (JSONArray) data.get(String.valueOf(studentId));
        //List<String>courses = new ArrayList<>();
        // Check if the student has already enrolled in the maximum number of courses
        if (courses != null && courses.size() >= MAX_COURSES) {
            throw new IllegalArgumentException("Student has already enrolled in the maximum number of courses");
        }
        // Check if the student is already enrolled in the course
        if (courses != null && courses.toJSONString().contains(String.valueOf(courseId))) {
            throw new IllegalArgumentException("Student is already enrolled in the course");
        }

        // Add the course to the student's record
        if (courses == null) {
            courses = new JSONArray();
            data.put(String.valueOf(studentId), courses);
        }
        courses.add(courseId);
        System.out.println("------------------");



        // Save the updated data to the JSON file
        try (FileWriter file = new FileWriter("src/data/test.json")) {
            file.write(data.toJSONString());
            System.out.println("Successfully updated json file");
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    public void unenrollStudent(int studentId, int courseId) {
        // Check if the student id and course id are valid
        if (!isValidStudentId(studentId)) {
            throw new IllegalArgumentException("Invalid student id");
        }
        if (!isValidCourseId(courseId)) {
            throw new IllegalArgumentException("Invalid course id");
        }
        JSONArray courses = (JSONArray) data.get(String.valueOf(studentId));
        //List<String>courses = new ArrayList<>();
        // Check if the student has already enrolled in the maximum number of courses
        if (courses != null && courses.size() <= MIN_COURSES) {
            throw new IllegalArgumentException("Student has enrolled in the minimum number of courses");
        }
        // Check if the student is already enrolled in the course


        // Add the course to the student's record
        if (courses == null | !courses.toJSONString().contains(String.valueOf(courseId))) {
            throw new IllegalArgumentException("Student isn't enrolled in the course");

        }
        for( Object obj : courses){
            System.out.println(obj);
            if (String.valueOf(courseId).contains(obj.toString())) {
                System.out.println(obj);
                courses.remove(obj);
                System.out.println(courses);
                break;
            }
        }
        System.out.println();

            System.out.println("------------------");
            //List<Integer>list = new ArrayList<>(courses) remove(courseId);


            // Save the updated data to the JSON file
            try (FileWriter file = new FileWriter("src/data/test.json")) {

                file.write(data.toJSONString());

                System.out.println("Successfully updated json file");
                file.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }


        }
    public void replaceStudent(int studentId, int old_course_id, int new_course_id) {
        // Check if the student id and course id are valid
        if (!isValidStudentId(studentId)) {
            throw new IllegalArgumentException("Invalid student id");
        }
        if (!isValidCourseId(old_course_id)) {
            throw new IllegalArgumentException("Invalid course id");
        }
        if(!isValidCourseId(new_course_id)){
            throw new IllegalArgumentException("Invalid course id");
        }
        JSONArray courses = (JSONArray) data.get(String.valueOf(studentId));
        //List<String>courses = new ArrayList<>();
        // Check if the student has already enrolled in the maximum number of courses
        if (courses != null && courses.size() <= MIN_COURSES) {
            throw new IllegalArgumentException("Student has enrolled in the minimum number of courses");
        }
        // Check if the student is already enrolled in the course


        // Add the course to the student's record
        if (courses == null | !courses.toJSONString().contains(String.valueOf(old_course_id))) {
            throw new IllegalArgumentException("Student isn't enrolled in the course");
        }
        if (courses == null | courses.toJSONString().contains(String.valueOf(new_course_id))) {
            throw new IllegalArgumentException("Student has already enrolled in the course");
        }

        for( Object obj : courses){
            System.out.println(obj);
            if (String.valueOf(old_course_id).contains(obj.toString())) {
                System.out.println(obj);
                courses.remove(obj);
                System.out.println(courses);
                break;
            }
        }
        courses.add(new_course_id);
        System.out.println("------------------");
        // Save the updated data to the JSON file
        try (FileWriter file = new FileWriter("src/data/test.json")) {

            file.write(data.toJSONString());

            System.out.println("Successfully updated json file");
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }


    }

    private boolean isValidStudentId(int studentId) {
        boolean valid = false;
        // Validation logic for student id
        if(studentId>0 && studentId<=100)
            valid=true;
        // Return true if the student id is valid, false otherwise
        return valid;
    }

    private boolean isValidCourseId(int courseId) {
        boolean valid = false;
        // Validation logic for course id
        if(courseId>0 && courseId<=17)
            valid=true;
        // Return true if the course id is valid, false otherwise
        return valid;
    }

public static void main(String[] args) throws IOException {
    EnrollmentManger enroll = new EnrollmentManger();
    //enroll.enroll(3,3);
    //enroll.enroll(4,4);
    //enroll.unenrollStudent(3,1);
    //enroll.unenrollStudent(5,4);
    //enroll.unenroll(5,6);
    enroll.replaceStudent(4,7,1);





}}

