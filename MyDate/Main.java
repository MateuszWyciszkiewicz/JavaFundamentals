import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

//TODO
//add data validation
//check for duplicates

class Main{
    private static String inputFile = "InputData.txt";
    private static String outputFile = "MyData.txt";
    public static void main(String[] args){
        ArrayList<String> lines = new ArrayList<String>();

        readLines(lines);

        ArrayList<MyData> dates = new ArrayList<MyData>();

        for(int i = 0; i< lines.size() ; i++){
            MyData element = new MyData();
            element = MyData.parseString(lines.get(i));
            System.out.println(element.returnDateString());
            dates.add(element);
        }
        writeLines(dates);
    }

    private static ArrayList<String> readLines(ArrayList<String> lines) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))){
            String line = bufferedReader.readLine();
            while(line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
        }catch(Exception e){
            System.out.println("File to read from not found");
            System.exit(1);
        }
        return lines;
    }    

    private static void writeLines(ArrayList<MyData> lines) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))){
           for(int i = 0; i<= lines.size(); i++){
            bufferedWriter.write(lines.get(i).returnDateString());
           }
        }catch(Exception e){
            System.out.println("Error during writing to file");
            System.exit(1);
        }
    }    
}

class MyData{
    public int day;
    public int month;
    public int year;
    public String weekday;
    private final static int weekdayIndex = 0;
    private final static int dateIndex = 1;
    private final static int dayIndex = 0;
    private  final static int monthIndex = 1;
    private final static int yearIndex = 2;

    public String returnDateString(){
        String dateString = "day = " + this.day + ", month = " + this.month + ", year = " + this.year + ", weekday = " + this.weekday + "\n";
        return dateString;
    }
    public static MyData parseString(String input){
        String[] splitedString = input.split(" ");
        splitedString = arrangeWeekdayAndDate(splitedString);
        System.out.println(splitedString[dateIndex]);
        MyData newData = decodeDate(splitedString[dateIndex]);
        newData.weekday = splitedString[weekdayIndex];
        return newData;

    }

    private static String[] arrangeWeekdayAndDate(String[] splitedString){
        String temp = new String();
        if(!splitedString[weekdayIndex].matches("[a-zA-Z]+")){
            temp = splitedString[weekdayIndex];
            splitedString[weekdayIndex] = splitedString[dateIndex];
            splitedString[dateIndex] = temp;
            return splitedString;
        }
        return splitedString;
    }

    private static MyData decodeDate(String date){
        String[] data = new String[3]; //remove this, return construtor, add default constructor
        if(date.matches("(.*)/(.*)/(.*)")){
            data = date.split("/");
            return assignDateInformation(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        } else if(date.matches("(.*)-(.*)-(.*)")){
            data = date.split("-");
            return assignDateInformation(Integer.parseInt(data[2]), Integer.parseInt(data[1]), Integer.parseInt(data[0]));
        } else if(date.matches("(.*)\\.(.*)\\.(.*)")){
            data = date.split("\\.");
            return assignDateInformation(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        }
        return assignDateInformation(-1, -1, -1);
    }

    private static MyData assignDateInformation(int day, int month, int year){
        MyData newData = new MyData();
        newData.day = day;
        newData.month = month;
        newData.year = year;
        return newData; 
    }
}