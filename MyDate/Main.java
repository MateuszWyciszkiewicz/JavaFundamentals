import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

//TODO
//add data validation
//check for duplicates

class Main{
    public static void main(String[] args){
       int rewritenLines =  MyData.convertDate();
       System.out.println("Number of rewriten dates: " + rewritenLines);
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

    private static String outputFile = "MyData.txt";
    private static String inputFile = "InputData.txt";


    private MyData(String dateString){
        this.parseString(dateString);
    }

    private String returnDateString(){
        String dateString = "day = " + this.day + ", month = " + this.month + ", year = " + this.year + ", weekday = " + this.weekday + "\n";
        return dateString;
    }
    
    private void parseString(String input){
        String[] splitedString = input.split(" ");
        splitedString = arrangeWeekdayAndDate(splitedString);
        this.weekday = splitedString[weekdayIndex];
        this.decodeDate(splitedString[dateIndex]);

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

    private void decodeDate(String date){
        String[] data = new String[3]; //remove this, return construtor, add default constructor
        if(date.matches("../(.*)/....")){
            data = date.split("/");
            this.assignDateInformation(data[dayIndex], data[monthIndex], data[yearIndex]);
        } else if(date.matches("....-..-..")){
            data = date.split("-");
           this.assignDateInformation(data[yearIndex], data[monthIndex], data[dayIndex]);
        } else if(date.matches("..\\...\\.....")){
            data = date.split("\\.");
           this.assignDateInformation(data[dayIndex], data[monthIndex], data[yearIndex]);
        } else{
        this.assignDateInformation("-1", "-1", "-1");
        }
    }

    private void assignDateInformation(String day, String month, String year){
       try{
        this.day = Integer.parseInt(day);
        this.month = Integer.parseInt(month);
        this.year = Integer.parseInt(year);
       } catch(Exception e){
        this.day = -1;
        this.month = -1;
        this.year = -1;
       }
    }

    public static int convertDate(){
        ArrayList<String> lines = new ArrayList<String>();

        readLines(lines);

        ArrayList<String> dates = new ArrayList<String>();

        for(int i = 0; i< lines.size() ; i++){
            MyData element = new MyData(lines.get(i));

            if(!dates.contains(element.returnDateString())){
                dates.add(element.returnDateString());
            }
        }
       int rewritenLines = writeLines(dates);
       return rewritenLines;
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

    private static int writeLines(ArrayList<String> lines) {
        int i = 0;
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))){
           for(i = 0; i<= lines.size()-1; i++){
            bufferedWriter.write(lines.get(i));
            i++;
           }
        }catch(Exception e){
            System.out.println("Error during writing to file");
            System.exit(1);
        }
        return i;
    }    
}