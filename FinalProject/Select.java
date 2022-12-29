import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Select {
    List<String> content;
    String filename;
    
    public Select(String query) {
        this.content = getTokens(query);
    }

    public void selectData()throws BadSyntaxException, IOException, BadFieldNameException, BadNumberOfArgumentsException{
        if(!this.content.get(0).equals("select") || !this.content.get(this.content.size()-2).equals("from")){
            throw new BadSyntaxException("Bad syntax", " select from", this.content.get(0) + " " + this.content.get(this.content.size()-2));
        }
        this.filename = this.content.get(this.content.size()-1) +".txt";
        String[] keys = getKeys();
        if( keys.length == 1 && keys[0].contentEquals("*")){
            readAll();
            return;
        }
        String[] headers = getHeaders();
        List<Integer> indexes = assignIndexes(keys, headers);
        readColumns(indexes);
    }

    private List<Integer> assignIndexes(String[] keys, String[]headers) throws BadNumberOfArgumentsException, BadFieldNameException{
        if( keys.length > headers.length) {
            throw new BadNumberOfArgumentsException("Bad number of arguments", headers.length, keys.length);
        }
        List<String> headersList = Arrays.asList(headers);
        List<Integer> indexes = new ArrayList<>();
        for(int i = 0; i < keys.length; i++){
            if(headersList.contains(keys[i])){
                indexes.add(headersList.indexOf(keys[i]));
            } else {
                throw new BadFieldNameException("bad field name", keys[i]);
            }
        }
        return indexes;
    }
    private void readAll() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			reader.close();
    }

    private void readColumns(List<Integer> indexes) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line = reader.readLine();

			while (line != null) {
                String[] tokens = line.split(" ");
                String output = "";
                for(int i = 0; i < indexes.size(); i ++){
                    output += tokens[indexes.get(i)] + " ";

                }
				System.out.println(output);
				line = reader.readLine();
			}
            reader.close();
    }
    private String[] getHeaders() throws IOException, FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line = reader.readLine();
        String[] headers = line.split(" ");
        reader.close();
        return headers;
    }

    public String[] getKeys(){
        int limit = this.content.indexOf("from");
        String keys = "";
        for(int i = 1; i < limit; i++){
            keys += this.content.get(i) + " ";
        }
        return keys.split(" ");
    }

    private static List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }
}
