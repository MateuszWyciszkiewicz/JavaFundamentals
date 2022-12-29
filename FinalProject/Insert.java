import java.io.IOException;
import java.io.Writer;

import Exceptions.BadNumberOfArgumentsException;
import Exceptions.BadSyntaxException;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Insert extends Query{

    public Insert(String query) {
        super(query);
    }

    public void insertNewData() throws BadSyntaxException, IOException, BadNumberOfArgumentsException {
        if (!this.content.get(0).equals("insert") || !this.content.get(1).equals("into")
                || !this.content.get(3).equals("values")) {
            String errorString = this.content.get(0) + " " + this.content.get(1) + " " + this.content.get(2);
            throw new BadSyntaxException("Bad Syntax", "insert into values",errorString);
        }
        this.filename = this.content.get(2) + ".txt";
        String[] headers = getHeaders();
        String input = inputString();
        if (headers.length != input.split("\t\t").length) {
            throw new BadNumberOfArgumentsException("Bad number of argument", headers.length, input.split(" ").length);
        }
        this.writeInput(input);
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
