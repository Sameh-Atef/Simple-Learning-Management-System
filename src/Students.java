import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Students extends Data{

    @Override
    public void displayData( ) {

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
            System.out.println(output.get(100));
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


}
