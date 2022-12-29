import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Insert extends Query{
    String filename;

    public Insert(String query) {
        super(query);
    }

    public void insertNewData() throws BadSyntaxException, IOException, BadNumberOfArgumentsException {
        if (!this.content.get(0).equals("insert") || !this.content.get(1).equals("into")
                || !this.content.get(3).equals("values")) {
            String errorString = this.content.get(0) + " " + this.content.get(1) + " " + this.content.get(2);
            throw new BadSyntaxException("Bad Syntax of insert into", "insert into *name* values",errorString);
        }
        this.filename = this.content.get(2) + ".txt";
        String[] headers = getHeaders();
        String input = inputString();
        if (headers.length != input.split(" ").length) {
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

    private String[] getHeaders() throws IOException, FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line = reader.readLine();
        String[] headers = line.split(" ");
        reader.close();
        return headers;
    }

    private String inputString() {
        String input = "";
        for (int i = 4; i < this.content.size(); i++) {
            input += this.content.get(i) + " ";
        }
        return input;
    }
}
