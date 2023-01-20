import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract class Data {
    static void writeData(List<String> output, String fileName) throws IOException {
        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(new File("src/data/"+fileName)));
        // using list
        for (String s : output) {
            writer.write(s);
            writer.newLine();
        }

        writer.flush();
        writer.close();

    }




}
