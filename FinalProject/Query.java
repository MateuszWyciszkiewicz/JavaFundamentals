import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Query {
    List<String> content;
    String filename;

    public Query(String query) {
        this.content = getTokens(query);
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
