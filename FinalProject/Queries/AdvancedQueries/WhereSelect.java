package Queries.AdvancedQueries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import Exceptions.BadFieldNameException;
import Exceptions.BadNumberOfArgumentsException;
import Exceptions.BadSyntaxException;
import Queries.BasicQueries.Select;

public class WhereSelect extends Select {

    public WhereSelect(String query) {
        super(query);
    }

    public void selectData() throws BadSyntaxException, FileNotFoundException, IOException, BadNumberOfArgumentsException, BadFieldNameException{
        if (!this.content.get(0).equalsIgnoreCase("select") || !this.content.get(this.content.size() - 6).equalsIgnoreCase("from")
                || !this.content.get(this.content.size() - 4).equals("where")) {
            throw new BadSyntaxException("Bad syntax", " select from where",
                    this.content.get(0) + " " + this.content.get(this.content.size() - 6) + " " +
                            this.content.get(this.content.size() - 4));
        }
        this.filename = this.content.get(this.content.size() - 5) + ".txt";
        String condition = this.content.get(this.content.size() - 2);
        String firstField = this.content.get(this.content.size() - 3);
        String secondField = this.content.get(this.content.size() - 1);
        String[] headers = getHeaders();
        String[] keys = getKeys();
        
        if (keys.length == 1 && keys[0].contentEquals("*")) {
            readAll(headers, condition, firstField, secondField);
            return;
        }
        List<Integer> indexes = assignIndexes(keys, headers);
        readColumns(indexes, headers, condition, firstField, secondField);
    }

    protected void readColumns(List<Integer> indexes, String[] headers, String condition, String firstField, String secondField) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        List<String> headersList = Arrays.asList(headers);
        String line = reader.readLine();

			while (line != null) {
                String[] tokens = line.split("\t\t");
                String output = "";
                for(int i = 0; i < indexes.size(); i ++){
                    output += tokens[indexes.get(i)] + "\t\t";

                }
                boolean insert = evaluateCondition(tokens[headersList.indexOf(firstField)], tokens[headersList.indexOf(secondField)], condition);
                if (insert){
				System.out.println(output);
                }
				line = reader.readLine();
			}
            reader.close();
    }

    private void readAll(String[] headers, String condition, String firstField, String secondField) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        List<String> headersList = Arrays.asList(headers);
			String line = reader.readLine();

			while (line != null) {
                String[] tokens = line.split("\t\t");
                boolean insert = evaluateCondition(tokens[headersList.indexOf(firstField)], tokens[headersList.indexOf(secondField)], condition);
                if(insert){
                    System.out.println(line);
                }
				line = reader.readLine();
			}

			reader.close();
    }

    private static boolean evaluateCondition(String firstField, String secondField, String condition){
        if(condition.equals(">")){
            return (firstField.compareTo(secondField) == 1);
        }
        if(condition.equals("<")){
            return (firstField.compareTo(secondField) == -1);
        }
        if(condition.equals("=")){
            return (firstField.compareTo(secondField) == 0);
        }
        return false;
    }
}
