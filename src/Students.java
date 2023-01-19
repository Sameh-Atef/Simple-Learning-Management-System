import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Students extends Data{
    private String id;
    private String name;
    private String email;
    private String address;
    private String region;
    private String country;



    public static void displayData() throws IOException {
        List<String>Student=new ArrayList<>();

        // creates scanner of that file path
        //Scanner scan = new Scanner(new File("src/data/student-data.csv"));
        // read till there aren't elements
        final File input = new File("src/data/student-data.csv");
        List<String> output = new ArrayList<>();
        BufferedReader reader = null;
        System.out.println("---------------------------------------------------------");
        System.out.println("Current Student List");
        System.out.println("---------------------------------------------------------");
        reader = new BufferedReader(new FileReader(input));
        String line = null;

        while ((line = reader.readLine()) != null) {

            // use comma as separator
            String[] fields = line.split(",");
            formatData(fields);

            System.out.println();
        }




    }
    public static void convertData(String fileName,String type){
        final File input = new File("src/data/"+fileName+"."+type);
        List<String> output = new ArrayList<>();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = null;
            String[] tokens = new String[0];
            while((line = reader.readLine()) != null){
                tokens = line.split("\\$");
            }
            for (String s: tokens){
                output.add(s.replace("#",","));
            }
//test output list by get method
           //System.out.println(output.get(100));
            reader.close();
//write into file .csv
            writer = new BufferedWriter(new FileWriter(new File("src/data/student-data.csv")));
            // using list
            for (String s : output) {
                writer.write(s);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // function used to format our csv data accord to requirements
    private static void formatData(String [] fields  ) {

        for(String field : fields) {
//search for pattern that can match only digit
            Pattern pattern1 = Pattern.compile("(\\S|\\d)");
            Matcher matcher1 = pattern1.matcher(field);
            if (matcher1.find()) {
                System.out.printf("%-37s",field);
            }
            else{
//expect to get non-digit data with this format

                System.out.printf("%-50s",field);
            }

    }

}}
