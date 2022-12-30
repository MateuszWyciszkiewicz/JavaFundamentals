import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;

import Exceptions.BadSyntaxException;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Delete extends Query {
    public Delete(String query){
        super(query);
    }

    public void deleteFromTable() throws BadSyntaxException, FileNotFoundException, IOException{
        if (!this.content.get(0).equals("delete") || !this.content.get(1).equals("from")) {
            String errorString = this.content.get(0) + " " + this.content.get(1) + " ";
            throw new BadSyntaxException("Bad Syntax of delete from", "delete from",errorString);
        }
        this.filename = this.content.get(2) + ".txt";
        String[] headers = super.getHeaders();
        delete(headers);

    }

    private void delete(String[] headers) throws IOException{
        writeHeaders(headersString(headers));
    }

    private void writeHeaders(String inputString) throws IOException{
        Writer input;
        input = new BufferedWriter(new FileWriter(this.filename, false)); 
        input.append(inputString + "\n");
        input.close();
    }

    private String headersString(String[] headers) {
        String input = "";
        for (int i = 0; i < headers.length; i++) {
            input += headers[i] + " ";
        }
        return input;
    }
}
