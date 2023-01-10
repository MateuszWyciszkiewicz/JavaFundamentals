package Queries.AdvancedQueries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import Exceptions.BadSyntaxException;
import Queries.BasicQueries.Delete;

public class WhereDelete extends Delete {

    public WhereDelete(String query) {
        super(query);
    }

    public void deleteFromTable() throws BadSyntaxException, FileNotFoundException, IOException {
        if (!this.content.get(0).equalsIgnoreCase("delete") || !this.content.get(1).equalsIgnoreCase("from")
                || !this.content.get(this.content.size() - 4).equalsIgnoreCase("where")) {
            String errorString = this.content.get(0) + " " + this.content.get(1) + " "
                    + this.content.get(this.content.size() - 4);
            throw new BadSyntaxException("Bad Syntax of WhereDelete", "delete from where", errorString);
        }
        this.filename = this.content.get(2) + ".txt";
        String condition = this.content.get(this.content.size() - 2);
        String firstField = this.content.get(this.content.size() - 3);
        String secondField = this.content.get(this.content.size() - 1);
        String[] headers = super.getHeaders();
        delete(headers, firstField, secondField, condition);
    }

    protected void delete(String[] headers, String firstField, String secondField, String condition)
        throws IOException {
        List<String> headersList = Arrays.asList(headers);
        BufferedReader reader = new BufferedReader(new FileReader(this.filename));
        String line = reader.readLine();

        File originalFile = new File(this.filename);
        File tempFile = new File("tempfile.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
        int iteration = 1;
        while (line != null) {
            String[] tokens = line.split("\t\t");
            if (iteration == 1) {
                writer.println(line);
                line = reader.readLine();
                iteration++;
                continue;
            }
            boolean delete = evaluateCondition(tokens[headersList.indexOf(firstField)],
            tokens[headersList.indexOf(secondField)], condition);
            if (!delete) {
                writer.println(line);
            }

            writer.flush();
            line = reader.readLine();
            iteration++;
        }
        writer.close();
        reader.close();
        if (!originalFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }
        if (!tempFile.renameTo(originalFile)) {
            System.out.println("Could not rename file");
        }
        System.out.println("Deletion from table successful");
    }

    private static boolean evaluateCondition(String firstField, String secondField, String condition) {
        if (condition.equals(">")) {
            try {
                return Integer.parseInt(firstField) > Integer.parseInt(secondField);
            } catch (Exception e) {
            }
            return (firstField.compareTo(secondField) > 0);
        }
        if (condition.equals("<")) {
            try {
                return Integer.parseInt(firstField) < Integer.parseInt(secondField);
            } catch (Exception e) {
            }
            return (firstField.compareTo(secondField) < 0);
        }
        if (condition.equals("=")) {
            try {
                return Integer.parseInt(firstField) == Integer.parseInt(secondField);
            } catch (Exception e) {
            }
            return (firstField.compareTo(secondField) == 0);
        }
        return false;
    }
}
