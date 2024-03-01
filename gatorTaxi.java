import java.io.*;
import java.util.ArrayList;

public class gatorTaxi {

    private static final String REGEX = "[^\\w\\s]";
    private static BufferedReader reader = null;

    public static void main(String[] args) throws IOException {
        String inputFile = args[0];
        //String inputFile = "C:/Users/shrom/Downloads/ADS - COP5536/Project/RB tree/input.txt";
        ArrayList<String[]> tokens = readInput(inputFile);

        Ride taxi = new Ride();

        //Initializing variables for main Loop
        int nextToken = 0;

        //Executing each of the functions
        while(nextToken < tokens.size()){
            String[] nextOperation = tokens.get(nextToken);
            if (nextOperation[0].startsWith("Insert"))
                taxi.Insert(Integer.valueOf(nextOperation[1]), Integer.valueOf(nextOperation[2]), Integer.valueOf(nextOperation[3]));
            else if(nextOperation[0].startsWith("GetNextRide"))
                taxi.GetNextRide();
            else if(nextOperation[0].startsWith("CancelRide"))
                taxi.CancelRide(Integer.valueOf(nextOperation[1]));
            else if(nextOperation[0].startsWith("UpdateTrip"))
                taxi.UpdateTrip(Integer.valueOf(nextOperation[1]), Integer.valueOf(nextOperation[2]));
            else if(nextOperation[0].startsWith("Print") && nextOperation.length == 2)
                taxi.Print(Integer.valueOf(nextOperation[1]));
            else if(nextOperation[0].startsWith("Print") && nextOperation.length == 3)
                taxi.Print(Integer.valueOf(nextOperation[1]), Integer.valueOf(nextOperation[2]));
            
            nextToken++;
        }

    }

    private static ArrayList<String[]> readInput(String inputFile) throws IOException {
        // Reads Input file given by user using bufferedReader and splits each instruction logically
        ArrayList<String[]> tokenArray = new ArrayList<>();
        reader = new BufferedReader(new FileReader(inputFile));
        String input;

        // Splitting the input function and parameters
        while ((input = reader.readLine()) != null) {
            String[] split = input.split(REGEX);
            for(int i=0;i<split.length;i++)
                split[i] = split[i].trim();
            tokenArray.add(split);
        }
        return tokenArray;
    }

}
