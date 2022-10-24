import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class MyDataWriter {
    public static ArrayList<String> readLines(ArrayList<String> lines, String inputFile) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))){
            String line = bufferedReader.readLine();
            while(line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
        }catch(Exception e){
            System.out.println("File to read from not found");
            System.exit(1);
        }
        return lines;
    }    

    public static int writeLines(ArrayList<String> lines, String outputFile) {
        int i = 0;
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))){
           for(i = 0; i<= lines.size()-1; i++){
            bufferedWriter.write(lines.get(i));
           }
        }catch(Exception e){
            System.out.println("Error during writing to file");
            System.exit(1);
        }
        return i;
    }   
}
