package Queries.BasicQueries;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import Exceptions.BadSyntaxException;
import Queries.Query;

public class Table extends Query{

    public Table(String query) {
        super(query);
    }

    public void createNewTable() throws BadSyntaxException, IOException {
        if(!this.content.get(0).equalsIgnoreCase("create") || !this.content.get(1).equalsIgnoreCase("table")){
            String errorString = this.content.get(0) + " " + this.content.get(1);
            throw new BadSyntaxException("Bad Syntax", "create table", errorString);
        }
        this.filename = this.content.get(2) + ".txt";
       if(this.createFile()){
            this.fillWithContent();
       }
       System.out.println("Query create table successfully executed");
    }

    private boolean  createFile() throws IOException{
        File myObj = new File(this.filename);
        if (myObj.createNewFile()) {
            return true;
        } 
        return false;
    }

    private void fillWithContent() throws IOException{
        String headers = "";
        for(int i = 3; i < this.content.size(); i++){
            headers += this.content.get(i) + "\t\t";
        }
        PrintWriter out = new PrintWriter(this.filename);
        out.println(headers);
        out.close();
    }
}
