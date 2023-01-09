import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exceptions.BadSyntaxException;

public class Update extends Query {
    public Update(String query){
        super(query);
    }

    public void updateTable() throws BadSyntaxException, IOException{
        if(!this.content.get(0).equalsIgnoreCase("update") || !this.content.get(2).equalsIgnoreCase("set")){
            throw new BadSyntaxException("Bad syntax", "update set", this.content.get(0) + " " + this.content.get(2));
        }
        this.filename = this.content.get(1) +".txt";
        Map<String, String> map = parseRowsAndValues();
        String[] headers = getHeaders();
        this.update(headers, map);

    }

    private void update(String[] headers, Map<String, String> map) throws IOException{
        List<String> headersList = Arrays.asList(headers);
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line = reader.readLine();

        File originalFile = new File(this.filename);
        File tempFile = new File("tempfile.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

			while (line != null) {
                String[] tokens = line.split("\t\t");
                String output = "";
                for(int i = 0; i < tokens.length; i++){
                    if(headersList.contains(tokens[i])){
                        output += tokens[i] + "\t\t";
                        continue;
                    }
                    if(map.containsKey(headersList.get(i))){
                        output += map.get(headersList.get(i)) + "\t\t";
                    } else {
                        output += tokens[i] + "\t\t";
                    }

                }
                writer.println(output);
                writer.flush();
				line = reader.readLine();
			}
            writer.close();
            reader.close();
        if (!originalFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }
        if (!tempFile.renameTo(originalFile)){
            System.out.println("Could not rename file");
        }
    }

    private Map<String, String> parseRowsAndValues(){
        Map <String, String> map = new HashMap<>();

        for(int i = 3; i < this.content.size(); i++){
            String[] values = this.content.get(i).split("=");
            map.put(values[0],  values[1]);
        }
        return map;
    }
}
