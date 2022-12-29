import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Table extends Query{

    public Table(String query) {
        super(query);
    }

    public void createNewTable() throws BadSyntaxException, IOException {
        if(!this.content.get(0).equals("create") || !this.content.get(1).equals("table")){
            String errorString = this.content.get(0) + " " + this.content.get(1);
            throw new BadSyntaxException("Bad Syntax of create table", "create table", errorString);
        }
        this.filename = this.content.get(2) + ".txt";
       if(this.createFile()){
            this.fillWithContent();
       }
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
