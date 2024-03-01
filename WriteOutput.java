import java.io.FileWriter;
import java.io.IOException;

public class WriteOutput {
    public void WriteOutput(){}
    
    public static void writeOutputToFile(String output, String filename) {
        try {
            // Create a FileWriter object with the specified filename
            FileWriter fileWriter = new FileWriter(filename, true);

            // Write the output string to the file
            fileWriter.write(output);

            // Close the FileWriter
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing output to file: " + e.getMessage());
        }
    }

}
