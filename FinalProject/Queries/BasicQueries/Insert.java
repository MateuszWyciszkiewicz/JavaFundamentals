package Queries.BasicQueries;
import java.io.IOException;
import java.io.Writer;

import Exceptions.BadNumberOfArgumentsException;
import Exceptions.BadSyntaxException;
import Queries.Query;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Insert extends Query{

    public Insert(String query) {
        super(query);
    }

    public void insertNewData() throws BadSyntaxException, IOException, BadNumberOfArgumentsException {
        if (!this.content.get(0).equalsIgnoreCase("insert") || !this.content.get(1).equalsIgnoreCase("into")
                || !this.content.get(3).equalsIgnoreCase("values")) {
            String errorString = this.content.get(0) + " " + this.content.get(1) + " " + this.content.get(2);
            throw new BadSyntaxException("Bad Syntax", "insert into values",errorString);
        }
        this.filename = this.content.get(2) + ".txt";
        String[] headers = getHeaders();
        String input = inputString();
        if (headers.length != input.split("\t\t").length) {
            throw new BadNumberOfArgumentsException("Bad number of argument", headers.length, input.split("\t\t").length);
        }
        this.writeInput(input);
        System.out.println("Insert into table successful");
    }

    private void writeInput(String inputString) throws IOException{
        Writer input;
        input = new BufferedWriter(new FileWriter(this.filename, true)); 
        input.append(inputString + "\n");
        input.close();
    }

    private String inputString() {
        String input = "";
        for (int i = 4; i < this.content.size(); i++) {
            input += this.content.get(i) + "\t\t";
        }
        return input;
    }
}
