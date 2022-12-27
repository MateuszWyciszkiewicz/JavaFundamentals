import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileWriter;


public class TestVectors {
    public static void main(String[] args) {
        ArrayList<Vector> vectors = new ArrayList<Vector>(Integer.parseInt(args[0]));
        boolean correctLengths = false;
		System.out.println("please insert " + args[0] + " vectors:");
        while (!correctLengths) {
            vectors = readVectors(Integer.parseInt(args[0]));
            try {
                Vector sum = Vector.sumVectors(vectors);
                writeToFile(sum.getVector().toString(), "result.txt");
                correctLengths = true;
            } catch (DifferentVectorsLengthException e) {
                e.printStackTrace();
                System.out.println("Inserted lengths of vectors:" + Arrays.toString(e.getLengths()));
                System.out.println("Please input vectors again");
                correctLengths = false;
            } catch (IOException e) {
                System.out.println("File error");
            } finally {
                vectors.clear();
            }
        }
    }

    private static ArrayList<Vector> readVectors(int count) {
        Scanner s = new Scanner(System.in);
            ArrayList<Vector> vectors = new ArrayList<Vector>();
            for (int counter = 0; counter < count; counter++) {
                String in = s.nextLine();
                vectors.add(new Vector(in));
            }
            return vectors;
    }

    private static void writeToFile(String in, String file) throws IOException{
        FileWriter writer = new FileWriter(file);
        writer.write(in);
        writer.close();
    }

}
