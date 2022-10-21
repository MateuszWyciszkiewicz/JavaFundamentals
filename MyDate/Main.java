import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

class Main{
    private static String outputFile = "MyData.txt";
    private static String inputFile = "InputData.txt";
    public static void main(String[] args){
       int rewritenLines =  MyData.convertDate(inputFile, outputFile);
       System.out.println("Number of rewriten dates: " + rewritenLines);
    }
}

class MyData{
    private int day;
    private int month;
    private int year;
    private String weekday;
    private final static int weekdayIndex = 0;
    private final static int dateIndex = 1;
    private final static int dayIndex = 0;
    private  final static int monthIndex = 1;
    private final static int yearIndex = 2;
    private final static int wrongDateValue = -1;

    private MyData(String dateString){
        this.parseString(dateString);
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
        String[] data = new String[3];
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
        this.day = wrongDateValue;
        this.month = wrongDateValue;
        this.year = wrongDateValue;
       }
    } 

    public static int convertDate(String inputFile, String outputFile){
        ArrayList<String> lines = new ArrayList<String>();

        readLines(lines, inputFile);

        ArrayList<String> dates = new ArrayList<String>();

        for(int i = 0; i< lines.size() ; i++){
            MyData element = new MyData(lines.get(i));

            if(element.ableToWrite(dates)){
                dates.add(element.returnDateString());
            }
        }
       int rewritenLines = writeLines(dates, outputFile);
       return rewritenLines;
    }

    private String returnDateString(){
        String dateString = "day = " + this.day + ", month = " + this.month + ", year = " + this.year + ", weekday = " + this.weekday + "\n";
        return dateString;
    }

    private boolean ableToWrite(ArrayList<String> dates){
        if(dates.contains(this.returnDateString()) || this.isWrongDate()){
            return false;
        }
        return true;
    }

    private boolean isWrongDate(){
        if(this.day == wrongDateValue || this.month == wrongDateValue || this.year == wrongDateValue){
            return true;
        }
        return false;
    }

    private static ArrayList<String> readLines(ArrayList<String> lines, String inputFile) {
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

    private static int writeLines(ArrayList<String> lines, String outputFile) {
        int i = 0;
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))){
           for(i = 0; i<= lines.size()-1; i++){
            bufferedWriter.write(lines.get(i));
           }
        }catch(Exception e){
            System.out.println("Error during writing to file");
            System.exit(1);
        }
        return i;
    }    
}