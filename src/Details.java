import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Details {

    public static void displayDetails(String student_id) throws IOException {
        displayHeader();
        if(isValid(student_id)){
        Students.displayData(student_id);

        displayDash();
        Courses.displayHeader1();
        if(isEmpty(getCourses(student_id)))
            System.out.println("This student hasn't enrolled in any courses");
        else
        {
        for(String course_id:getCourses(student_id)){
             Courses.displayData(course_id);
             //System.out.println(course_id);
             }
        }}
        else
        System.out.println("Invalid Student ID");
        displayDash();

    }
    //get data from json provide student id and we get courses id
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
    private static boolean isValid(String student_id){
        int number = Integer.parseInt(student_id);
        if(number>100 | number<0)
             return false;
         else
            return true;
    }



}

