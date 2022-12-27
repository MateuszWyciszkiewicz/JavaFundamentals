import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
            try {
                Table table = new Table(query);
                table.createNewTable();
            } catch (BadSyntaxException e) {
                e.printStackTrace();
                System.out.println("bad syntax, want: " + e.want + " received: " + e.received);
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IOException occured");
                System.exit(1);
            }
        } else if (query.contains("insert into")) {

            try {
                Insert insert = new Insert(query);
                insert.insertNewData();
            } catch (BadSyntaxException e) {
                e.printStackTrace();
                System.out.println("bad syntax, want: " + e.want + " received: " + e.received);
                System.exit(1);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IOException occured");
                System.exit(1);
            }
        }
    }

    public static String prepareString(String query) {
        String newQuery;
        newQuery = query.replaceAll("\\p{P}", "");
        return newQuery;
    }
}
