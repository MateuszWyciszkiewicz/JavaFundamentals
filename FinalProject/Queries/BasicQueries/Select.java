package Queries.BasicQueries;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Exceptions.BadFieldNameException;
import Exceptions.BadNumberOfArgumentsException;
import Exceptions.BadSyntaxException;
import Queries.Query;

public class Select extends Query{

    public Select(String query) {
        super(query);
    }

    public void selectData()throws BadSyntaxException, IOException, BadFieldNameException, BadNumberOfArgumentsException{
        if(!this.content.get(0).equalsIgnoreCase("select") || !this.content.get(this.content.size()-2).equalsIgnoreCase("from")){
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

    protected List<Integer> assignIndexes(String[] keys, String[]headers) throws BadNumberOfArgumentsException, BadFieldNameException{
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
    protected void readAll() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			reader.close();
    }

    protected void readColumns(List<Integer> indexes) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line = reader.readLine();

			while (line != null) {
                String[] tokens = line.split("\t\t");
                String output = "";
                for(int i = 0; i < indexes.size(); i ++){
                    output += tokens[indexes.get(i)] + "\t\t";

                }
				System.out.println(output);
				line = reader.readLine();
			}
            reader.close();
    }

    public String[] getKeys(){
        int limit = this.content.indexOf("from");
        String keys = "";
        for(int i = 1; i < limit; i++){
            keys += this.content.get(i) + " ";
        }
        return keys.split(" ");
    }
}
