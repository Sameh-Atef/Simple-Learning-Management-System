import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Courses extends Data {




    @Override
    public void displayData() {


    }
    public static void convertData(String fileName,String type) throws IOException {
        final File input = new File("src/data/"+fileName+"."+type);
        List<String> output = new ArrayList<>();
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(input));
            String line = null;
            String[] tokens = new String[0];
            String words = null;
            //String line = StringUtils.EMPTY;
            output.add("id" + "," + "CourseName" + "," + "Instructor" + "," + "CourseDuration" + "," + "CourseTime" + "," + "Location");


            while ((line = reader.readLine()) != null) {
                if(line.contains("id")) {
                    String id = extractValue(line,"<id>","</id>");
                    String CourseName = extractValue(reader.readLine(),"<CourseName>","</CourseName>");
                    String Instructor = extractValue(reader.readLine(),"<Instructor>","</Instructor>").replaceAll(",",". ");
                    String CourseDuration = extractValue(reader.readLine(),"<CourseDuration>","</CourseDuration>");
                    String CourseTime = extractValue(reader.readLine(),"<CourseTime>","</CourseTime>");
                    String Location = extractValue(reader.readLine(),"<Location>","</Location>");
                    output.add(id + "," + CourseName + "," + Instructor + "," + CourseDuration + "," + CourseTime + "," + Location);
                }


            }

            reader.close();
            writer = new BufferedWriter(new FileWriter(new File("src/data/coursedata.csv")));
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
        }

            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        private static String extractValue(String line,String start, String end ) {
        //pattern to parser xml created by trying and error
            Pattern pattern = Pattern.compile(start+"(\\S+|\\S+ \\S+|\\S+?, \\S+ \\S.|\\S+ \\S+.|\\S+ \\S+ . |\\S+ \\S+ \\S+.|\\S+ \\S+ &amp; \\S+.)"+end);
            Matcher matcher = pattern.matcher(line);
            String str = null;
            if (matcher.find()) {
                //str = null;
                str = matcher.group(1);
                //System.out.println(str);
            }
            //return str;
            return str;
            //return "Not-found";
        }
    }

