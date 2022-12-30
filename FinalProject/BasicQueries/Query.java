import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Query {
    List<String> content;
    String filename;

    public Query(String query) {
        this.content = getTokens(query);
    }

    protected String[] getHeaders() throws IOException, FileNotFoundException{
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line = reader.readLine();
        String[] headers = line.split("\t\t");
        reader.close();
        return headers;
    }

    private static List<String> getTokens(String str) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        while (tokenizer.hasMoreElements()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }
}
