import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Exceptions.BadFieldNameException;
import Exceptions.BadNumberOfArgumentsException;
import Exceptions.BadSyntaxException;

class Main {
    public static void main(String[] args) {
        System.out.println("Input query:");
        String query = new String();
        try {
            query = readQuery();
        } catch (IOException e) {
            System.out.println("IOException occured");
            System.exit(1);
        }
        parseQuery(query);
    }

    public static String readQuery() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String query = reader.readLine();
        return query;
    }

    public static void parseQuery(String query) {
        query = prepareString(query);
        if (query.contains("create table")) {
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

    private static void attemptCreateTable(String query){
        try {
            Table table = new Table(query);
            table.createNewTable();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
            System.exit(1);
        }
    }

    private static void attemptInsertInto(String query){
        try {
            Insert insert = new Insert(query);
            insert.insertNewData();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
            System.exit(1);
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
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
            System.exit(1);
        } catch (BadNumberOfArgumentsException e) {
            e.printStackTrace();
            System.out.println("Bad number of arguments, want:" + e.getWant() + " received: " + e.getReceived());
            System.exit(1);
        }catch (BadFieldNameException e) {
            e.printStackTrace();
            System.out.println("No field " + e.getFieldname() +" in the table");
            System.exit(1);
        }
    }

    private static void attemptDeleteFrom(String query) {
        try {
            Delete delete = new Delete(query);
            delete.deleteFromTable();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
            System.exit(1);
        } 
    }

    private static void attemptUpdate(String query){
        try {
            Update update = new Update(query);
            update.updateTable();
        } catch (BadSyntaxException e) {
            e.printStackTrace();
            System.out.println("bad syntax, want: " + e.getWant() + " received: " + e.getReceived());
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException occured");
            System.exit(1);
        } 
    }

    public static String prepareString(String query) {
        String newQuery;
        newQuery = query.replaceAll("[()]", "");
        newQuery = newQuery.replaceAll(",", "");
        return newQuery;
    }

}
