import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exceptions.BadFieldNameException;
import Exceptions.BadNumberOfArgumentsException;
import Exceptions.BadSyntaxException;

public class GroupBy extends Select {
    GroupBy(String query) {
        super(query);
    }

    public void selectData()
            throws BadSyntaxException, IOException, BadFieldNameException, BadNumberOfArgumentsException {
        if (!this.content.get(0).equalsIgnoreCase("select") || !this.content.get(this.content.size() - 5).equalsIgnoreCase("from")
                || !this.content.get(this.content.size() - 3).equalsIgnoreCase("group")
                || !this.content.get(this.content.size() - 2).equalsIgnoreCase("by")) {
            throw new BadSyntaxException("Bad syntax", " select from group by",
                    this.content.get(0) + " " + this.content.get(this.content.size() - 2) +
                            this.content.get(this.content.size() - 3) + " " +
                            this.content.get(this.content.size() - 2));
        }
        this.filename = this.content.get(this.content.size() - 4) + ".txt";
        String[] headers = getHeaders();
        String[] keys = getKeys();
        int groupIndex = getGroupIndex(headers, this.content.get(this.content.size() - 1));
        if (keys.length == 1 && keys[0].contentEquals("*")) {
            Map<String, int[]> tableMap = readAll(groupIndex, headers);
            printMap(tableMap, headers, null);
            return;
        }
        List<Integer> indexes = assignIndexes(keys, headers);
        Map<String, int[]> tableMap = readColumns(groupIndex, headers, indexes);
        printMap(tableMap, headers, indexes);

    }

    protected Map<String, int[]> readAll(int groupIndex, String[] headers) throws IOException {
        Map<String, int[]> tableMap = new HashMap<>();
        List<String> headersList = Arrays.asList(headers);
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line = reader.readLine();
        while (line != null) {
            boolean insert = true;
            String[] tokens = line.split("\t\t");
            int count[] = new int[tokens.length];
            String key = new String();
            for (int i = 0; i < tokens.length; i++) {
                if (headersList.contains(tokens[i])) {
                    insert = false;
                    continue;
                }
                if (i != groupIndex) {
                    count[i]++;
                }
                if (i == groupIndex && !tableMap.containsKey(tokens[i])) {
                    tableMap.put(tokens[i], null);
                    key = tokens[i];

                } else if (i == groupIndex) {
                    key = tokens[i];
                }
            }
            if (tableMap.get(key) == null && insert == true) {
                tableMap.put(key, count);
            } else if (insert == true) {
                for (int i = 0; i < tableMap.get(key).length; i++) {
                    tableMap.get(key)[i] += count[i];
                }
            }
            line = reader.readLine();
        }
        reader.close();
        return tableMap;
    }

    protected Map<String, int[]> readColumns(int groupIndex, String[] headers, List<Integer> indexes)
            throws FileNotFoundException, IOException {
        Map<String, int[]> tableMap = new HashMap<>();
        List<String> headersList = Arrays.asList(headers);
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line = reader.readLine();
        while (line != null) {
            boolean insert = true;
            String[] tokens = line.split("\t\t");
            int count[] = new int[tokens.length];
            String key = new String();
            for (int i = 0; i < tokens.length; i++) {
                if (headersList.contains(tokens[i])) {
                    insert = false;
                    continue;
                }
                if (!indexes.contains(i)) {
                    continue;
                }
                if (i != groupIndex) {
                    count[i]++;
                }
                if (i == groupIndex && !tableMap.containsKey(tokens[i])) {
                    tableMap.put(tokens[i], null);
                    key = tokens[i];

                } else if (i == groupIndex) {
                    key = tokens[i];
                }
            }
            if (tableMap.get(key) == null && insert == true) {
                tableMap.put(key, count);
            } else if (insert == true) {
                for (int i = 0; i < tableMap.get(key).length; i++) {
                    tableMap.get(key)[i] += count[i];
                }
            }
            line = reader.readLine();
        }
        reader.close();
        return tableMap;
    }

    private int getGroupIndex(String[] headers, String fieldname) throws BadFieldNameException {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].contentEquals(fieldname)) {
                return i;
            }
        }
        throw new BadFieldNameException("Wrong fieldname", fieldname);
    }

    private static void printMap(Map<String, int[]> map, String[] headers, List<Integer> indexes) {
        System.out.println(prepareHeaders(headers, indexes));
        for (Map.Entry<String, int[]> entry : map.entrySet()) {
            System.out.print(entry.getKey() + "\t\t");
            printContent(entry.getValue());
        }
    }

    private static void printContent(int[] content) {
        for (int i = 0; i < content.length; i++) {
            if (content[i] != 0) {
                System.out.print(content[i] + "\t\t");
            }
        }
        System.out.print("\n");
    }

    private static String prepareHeaders(String[] headers, List<Integer> indexes) {
        String output = "";
        for (int i = 0; i < headers.length; i++) {
            if (indexes == null) {
                output += headers[i] + "\t\t";
            } else if (indexes.contains(i)) {
                output += headers[i] + "\t\t";
            }
        }
        return output;
    }
}
