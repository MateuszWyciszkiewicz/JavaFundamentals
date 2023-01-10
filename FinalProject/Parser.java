import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Exceptions.BadFieldNameException;
import Exceptions.BadNumberOfArgumentsException;
import Exceptions.BadSyntaxException;
import Queries.AdvancedQueries.GroupBy;
import Queries.AdvancedQueries.WhereDelete;
import Queries.AdvancedQueries.WhereSelect;
import Queries.AdvancedQueries.WhereUpdate;
import Queries.BasicQueries.Delete;
import Queries.BasicQueries.Insert;
import Queries.BasicQueries.Select;
import Queries.BasicQueries.Table;
import Queries.BasicQueries.Update;

class Parser {
    public static void executeQuery(String query) {
        System.out.println("Result of query \"" + query +"\":");
        parseQuery(query);
    }

    public static String readQuery() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String query = reader.readLine();
        return query;
    }

    public static void parseQuery(String query) {
        query = prepareString(query);
        if(query.contains("where")){
            attemptWhere(query);
        } else if(query.contains("group by")){
            attemptGroupBy(query);
        } else if (query.contains("create table")) {
            attemptCreateTable(query);
        } else if (query.contains("insert into")) {
            attemptInsertInto(query);
        } else if (query.contains("select")){
            attemptSelect(query);
        } else if (query.contains("delete from")){
            attemptDeleteFrom(query);
        } else if (query.contains("update")){
            attemptUpdate(query);
        }
    }

    private static void attemptWhere(String query){
        if(query.contains("select")){
            attemptWhereSelect(query);
        } else if (query.contains("update")){
            attemptWhereUpdate(query);
        } else if (query.contains("delete")){
            attemptWhereDelete(query);
        }
    }

    private static void attemptWhereSelect(String query){
        try {
            WhereSelect where = new WhereSelect(query);
            where.selectData();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
        } catch (BadNumberOfArgumentsException e) {
            e.printStackTrace();
            System.out.println("Bad number of arguments, want:" + e.getWant() + " received: " + e.getReceived());
        }catch (BadFieldNameException e) {
            e.printStackTrace();
            System.out.println("No field " + e.getFieldname() +" in the table");
        }
    }

    private static void attemptWhereUpdate(String query){
        try {
            WhereUpdate update = new WhereUpdate(query);
            update.updateTable();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
        } 
    }

    private static void attemptWhereDelete(String query){
        try {
            WhereDelete delete = new WhereDelete(query);
            delete.deleteFromTable();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
        } 
    }

    private static void attemptGroupBy(String query){
        try {
            GroupBy groupBy = new GroupBy(query);
            groupBy.selectData();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
        } catch (BadNumberOfArgumentsException e) {
            e.printStackTrace();
            System.out.println("Bad number of arguments, want:" + e.getWant() + " received: " + e.getReceived());
        }catch (BadFieldNameException e) {
            e.printStackTrace();
            System.out.println("No field " + e.getFieldname() +" in the table");
        }
    }

    private static void attemptCreateTable(String query){
        try {
            Table table = new Table(query);
            table.createNewTable();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
        }
    }

    private static void attemptInsertInto(String query){
        try {
            Insert insert = new Insert(query);
            insert.insertNewData();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
        } catch (BadNumberOfArgumentsException e) {
            e.printStackTrace();
            System.out.println("Bad number of arguments, want:" + e.getWant() + " received: " + e.getReceived());
        }
    }

    private static void attemptSelect(String query) {
        try {
            Select select = new Select(query);
            select.selectData();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
        } catch (BadNumberOfArgumentsException e) {
            e.printStackTrace();
            System.out.println("Bad number of arguments, want:" + e.getWant() + " received: " + e.getReceived());
        }catch (BadFieldNameException e) {
            e.printStackTrace();
            System.out.println("No field " + e.getFieldname() +" in the table");
        }
    }

    private static void attemptDeleteFrom(String query) {
        try {
            Delete delete = new Delete(query);
            delete.deleteFromTable();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
        } 
    }

    private static void attemptUpdate(String query){
        try {
            Update update = new Update(query);
            update.updateTable();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
        } 
    }

    public static String prepareString(String query) {
        String newQuery;
        newQuery = query.replaceAll("[()]", "");
        newQuery = newQuery.replaceAll(",", "");
        return newQuery;
    }

}
